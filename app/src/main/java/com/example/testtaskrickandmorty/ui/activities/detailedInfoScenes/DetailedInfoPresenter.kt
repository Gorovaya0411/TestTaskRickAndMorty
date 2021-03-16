package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedInfoPresenter @Inject constructor(private val charactersDetailedUseCase: CharactersDetailedUseCase) :
    MvpPresenter<DetailedInfoView>() {
    fun getEpisodes(string: String) {
        val getEpisodes = charactersDetailedUseCase.getEpisodes(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
//                val employee = AnswerResult()
                val listEpisode = arrayListOf<String>()
                var increment = 1
                list.forEach {
                    listEpisode.add("$increment)${it.name}")
                    increment++
                }
//                employee.episodes = listEpisode
//                viewState.showEpisodes(listEpisode)
            }, {

            })
    }

    fun getEpisode(string: String) {
        val getEpisode = charactersDetailedUseCase.getEpisode(string)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

//                val episode = AnswerResult()
//                episode.episode = it.name
            }, {

            })
    }
}