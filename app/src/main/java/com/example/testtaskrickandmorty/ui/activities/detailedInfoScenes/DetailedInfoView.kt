package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface DetailedInfoView : MvpView {
}