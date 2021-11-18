package com.example.storygenerator.presentation.contracts

import com.example.storygenerator.domain.modeles.Content
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListContentsContractsView: MvpView {
    fun showContents(listContent: List<Content>)

    fun showDialog()

    fun showProgressBar()

    fun hideProgressBar()
}