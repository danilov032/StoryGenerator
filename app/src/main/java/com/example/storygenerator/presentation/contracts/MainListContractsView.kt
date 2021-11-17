package com.example.storygenerator.presentation.contracts

import com.example.storygenerator.domain.modeles.Content
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface MainListContractsView: MvpView {
    fun getContents()
}