package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedInfoPresenter @Inject constructor(private val charactersDetailedUseCase: CharactersDetailedUseCase) :
    MvpPresenter<DetailedInfoView>() {
    fun getEpisodes(string: String, id: Int) {
        val getEpisodes = charactersDetailedUseCase.getEpisodes(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

                val listEpisode = arrayListOf<String>()
                var increment = 1
                list.forEach {
                    listEpisode.add("$increment)${it.name}")
                    increment++
                }

                viewState.showEpisodes(listEpisode)
            }, {
                get(id)
            })
    }

    fun getEpisode(string: String, id: Int) {
        val getEpisode = charactersDetailedUseCase.getEpisode(string)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showEpisode(it.name)

            }, {
                get1(id)
            })
    }

    fun get(id: Int) {
        val disposable = charactersDetailedUseCase.get(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.episodesCharacters?.let { it1 -> viewState.showEpisodes(it1) }
            }, {

            })
    }

    fun get1(id: Int) {
        val disposable = charactersDetailedUseCase.get(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showEpisode(it.episodeCharacters)
            }, {

            })
    }
}