package com.example.storygenerator.presentation.presenters

import com.example.storygenerator.domain.interactors.Interactor
import com.example.storygenerator.presentation.contracts.MainListContractsView
import com.example.storygenerator.presentation.utils.Categories
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainListPresenter @Inject constructor(private val interactor: Interactor): MvpPresenter<MainListContractsView>()  {
    private val disposable = CompositeDisposable()

    fun onClickCategory(category: Categories){
        viewState.openCurrentContent(category, false)
    }

    fun startActivity(){
        disposable.add(
            interactor.getContentsWithBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contents ->
                    if(contents.isNotEmpty()){
                        viewState.openCurrentContent(Categories.getClassEnum(contents[0].idCategory), true)
                    }
                    else{
                        viewState.showContents(Categories.values().toList())
                    }
                }, {
                    viewState.showErrorMessage()
                })
        )
    }
}