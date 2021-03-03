package com.example.testtaskrickandmorty.ui.activities.mainScenes

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {

    private val apiService = RickAndMortyApiService.create()
    private var increment: Int = 1
    private var isRequest: Boolean = false


    fun swipeRefresh() {
        increment = 1
        val subscribe = apiService.getCharacter(page = increment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showInternet()
                viewState.populateData(it.results)
            }, {
                viewState.showNoInternet()
            })
    }

    fun getMoreItems() {
        if (!isRequest) {
            isRequest = true
            increment += 1
            viewState.visibilityProgressBar(true)
            val subscribe = apiService.getCharacter(page = increment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    viewState.addData(it.results)
                    isRequest = false
                    viewState.visibilityProgressBar(false)
                }, { error ->
                    error.printStackTrace()
                })
        }
    }
}