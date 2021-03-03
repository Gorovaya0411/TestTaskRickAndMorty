package com.example.testtaskrickandmorty.ui.activities.mainScenes

import com.example.testtaskrickandmorty.data.model.AnswerResults
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface MainView : MvpView {
    fun showInternet()
    fun showNoInternet()
    fun populateData(model: List<AnswerResults>)
    fun visibilityProgressBar(isVisible: Boolean)
    fun addData(model: List<AnswerResults>)
}