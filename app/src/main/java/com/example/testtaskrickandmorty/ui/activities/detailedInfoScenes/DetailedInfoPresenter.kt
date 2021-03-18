package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import com.example.testtaskrickandmorty.data.model.Episode
import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedInfoPresenter @Inject constructor(private val charactersDetailedUseCase: CharactersDetailedUseCase) :
    MvpPresenter<DetailedInfoView>() {

    private val listEpisode = arrayListOf<String>()
    fun getEpisodes(string: String, id: Int) {
        val getEpisodes = charactersDetailedUseCase.getEpisodes(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val episode = Episode()
                episode.id = id

                var increment = 1
                list.forEach {
                    listEpisode.add("$increment)${it.name}")
                    increment++
                }
                episode.nameEpisodesCharacters = listEpisode
                insertEpisodesInEpisode(episode)
                viewState.showEpisodes(listEpisode)
            }, {
                getInfoByEpisodes(id)
            })
    }

    fun getEpisode(string: String, id: Int) {
        val getEpisode = charactersDetailedUseCase.getEpisode(string)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val episode = Episode()
                episode.nameEpisodeCharacters = it.name
                episode.id = id
                viewState.showEpisode(it.name)
                insertEpisodesInEpisode(episode)

            }, {
                getInfoByEpisode(id)
            })
    }

    private fun insertEpisodesInEpisode(episode: Episode) {
        val disposable =
            charactersDetailedUseCase.insertEpisodesInEpisode(episode).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {

                })
    }

    private fun getInfoByEpisode(id: Int) {
        val disposable =
            charactersDetailedUseCase.getInfoByEpisode(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.showEpisode(it.nameEpisodeCharacters)
                }, {

                })
    }

    private fun getInfoByEpisodes(id: Int) {
        val disposable =
            charactersDetailedUseCase.getInfoByEpisode(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.nameEpisodesCharacters?.let { it1 -> viewState.showEpisodes(it1) }
                }, {

                })
    }

    fun getCharactersByID(id: Int) {
        val disposable =
            charactersDetailedUseCase.getCharactersByID(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.getOneCharacters(it)
                }, {

                })
    }

}