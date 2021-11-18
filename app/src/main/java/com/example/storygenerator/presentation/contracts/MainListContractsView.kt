package com.example.storygenerator.presentation.contracts

import com.example.storygenerator.presentation.utils.Categories
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface MainListContractsView: MvpView {
    fun showContents(listCategory: List<Categories>)

    fun openCurrentContent(category: Categories, statusGetData: Boolean)
}