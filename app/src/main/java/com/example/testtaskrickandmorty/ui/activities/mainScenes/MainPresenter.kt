package com.example.testtaskrickandmorty.ui.activities.mainScenes

import com.example.testtaskrickandmorty.MyApplication
import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.repository.CharactersMainRepository
import com.example.testtaskrickandmorty.domain.CharactersMainUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var apiService: RickAndMortyApiService
    private var increment: Int = 1
    private var isRequest: Boolean = false
    private val charactersMainUseCase = CharactersMainUseCase(CharactersMainRepository(apiService))

    fun swipeRefresh() {

       val disposable = charactersMainUseCase.swipeRefresh()
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
            val disposable = apiService.getCharacter(page = increment)
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
    MyApplication.appComponent.inject(this)
}