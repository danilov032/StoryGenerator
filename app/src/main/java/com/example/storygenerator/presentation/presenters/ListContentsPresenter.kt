package com.example.storygenerator.presentation.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.example.storygenerator.domain.interactors.Interactor
import com.example.storygenerator.domain.modeles.Content
import com.example.storygenerator.presentation.contracts.ListContentsContractsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListContentsPresenter @Inject constructor(private val interactor: Interactor): MvpPresenter<ListContentsContractsView>() {
    var count = 10
    private var subject: BehaviorSubject<Int> = BehaviorSubject.createDefault(count)
    private val list = mutableListOf<String>()
    private var idCategory: Int = 1

    fun onStartActivity(id: Int){
        idCategory = id
        createSubject()
    }

    @SuppressLint("CheckResult")
    fun getData(){
        interactor.getContent(idCategory)
            .retry(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                Log.d("AAA", count.toString())
                list.add(item.content)
                count--
                subject.onNext(count)
            }, {
                count--
                Log.d("AAA", it.message.toString())
                subject.onNext(count)
            })
    }

    @SuppressLint("CheckResult")
    fun createSubject(){
        subject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ count ->
                if (count > 0) getData()
                else dela()
            }, {

            })
    }

    fun dela(){
        Log.d("AAA", list.size.toString())
        Log.d("AAA", "data")
        val list1 = list.map {
            Content(it)
        }
        viewState.showContents(list1)
    }
}