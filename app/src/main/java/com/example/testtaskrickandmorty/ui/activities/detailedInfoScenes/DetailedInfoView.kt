package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import com.example.testtaskrickandmorty.data.model.AnswerResults
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface DetailedInfoView : MvpView {
    fun showEpisodes(listEpisode: List<String>)
    fun showEpisode(episode: String)
    fun getOneCharacters(model: AnswerResults)
}