package com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class DetailedInfoPresenter : MvpPresenter<DetailedInfoView>() {
    private val apiService = RickAndMortyApiService.create()
    fun getEpisodes(string: String) {
        val getEpisodes = apiService.getEpisodes(string)
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

            })
    }

    fun getEpisode(string: String) {
        val getEpisode = apiService.getEpisode(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showEpisode(it.name)
            }, {

            })
    }
}