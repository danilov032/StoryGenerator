package com.example.storygenerator.presentation.presenters

import com.example.storygenerator.domain.interactors.Interactor
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.contracts.ListContentsContractsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListContentsPresenter @Inject constructor(private val interactor: Interactor) :
    MvpPresenter<ListContentsContractsView>() {
    private val disposable = CompositeDisposable()
    var count = 10
    private var subject: BehaviorSubject<Int> = BehaviorSubject.createDefault(count)
    private val list = mutableListOf<String>()
    private var idCategory: Int = 1
    private var loading: Boolean = false

    fun onStartActivity(id: Int, statusGetData: Boolean) {
        idCategory = id
        viewState.showProgressBar()
        if (statusGetData) {
            disposable.add(
                interactor.getContentsWithBD()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ content ->
                        viewState.hideProgressBar()
                        viewState.showContents(content)
                    }, {
                        viewState.showErrorMessage()
                    })
            )
        } else {
            createSubject()
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    fun repeatData() {
        getData()
    }

    fun clickBack() {
        disposable.add(
            interactor.deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    viewState.showErrorMessage()
                })
        )
    }

    fun updateData() {
        if (!loading) {
            loading = true
            count = 10
            list.clear()
            subject = BehaviorSubject.createDefault(count)
            viewState.showProgressBar()
            createSubject()
        }
    }

    private fun getData() {
        disposable.add(
            interactor.getContent(idCategory)
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    list.add(item.content)
                    count--
                    subject.onNext(count)
                }, {
                    viewState.showDialog()
                })
        )
    }

    private fun createSubject() {
        disposable.add(
            subject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ count ->
                    if (count > 0) getData()
                    else {
                        if (loading) addContent()
                        else showContent()
                        loading = false
                    }
                }, {
                    viewState.showDialog()
                })
        )
    }

    private fun addContent() {
        clickBack()
        val listContent = list.map {
            Content(idCategory, it)
        }

        disposable.add(
            interactor.insertContents(listContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    viewState.showErrorMessage()
                })
        )
        viewState.hideProgressBar()
        viewState.addDataRecycler(listContent)
    }

    private fun showContent() {
        val listContent = list.map {
            Content(idCategory, it)
        }
        disposable.add(
            interactor.insertContents(listContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {
                    viewState.showErrorMessage()
                })
        )
        viewState.hideProgressBar()
        viewState.showContents(listContent)
    }
}