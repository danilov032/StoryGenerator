package com.example.storygenerator.presentation.presenters

import com.example.storygenerator.domain.interactors.Interactor
import com.example.storygenerator.presentation.contracts.MainListContractsView
import com.example.storygenerator.presentation.utils.Categories
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainListPresenter @Inject constructor(private val interactor: Interactor): MvpPresenter<MainListContractsView>()  {
    override fun attachView(view: MainListContractsView?) {
        super.attachView(view)
        viewState.showContents(Categories.values().toList())
    }

    fun onClickCategory(category: Categories){
        viewState.openCurrentContent(category)
    }
}