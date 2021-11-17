package com.example.storygenerator.presentation.presenters

import android.annotation.SuppressLint
import android.util.Log
import com.example.storygenerator.domain.interactors.Interactor
import com.example.storygenerator.presentation.contracts.MainListContractsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.BehaviorSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainListPresenter @Inject constructor(private val interactor: Interactor): MvpPresenter<MainListContractsView>()  {
    var count = 10
    private var subject: BehaviorSubject<Int> = BehaviorSubject.createDefault(count)
    private val list = mutableListOf<String>()

    override fun attachView(view: MainListContractsView?) {
        super.attachView(view)

        createSubject()
    }

    @SuppressLint("CheckResult")
    fun getData(){
        interactor.getContent(1)
            .retry(3)
            .subscribeOn(io())
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
    }
}