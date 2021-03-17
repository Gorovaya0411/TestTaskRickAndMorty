package com.example.testtaskrickandmorty.ui.activities.mainScenes

import com.example.testtaskrickandmorty.domain.CharactersMainUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val charactersMainUseCase: CharactersMainUseCase) :
    MvpPresenter<MainView>() {

    private var isRequest: Boolean = false
    fun swipeRefresh() {

        val disposable = charactersMainUseCase.swipeRefresh()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.populateData(it.results)
            }, {
                get()
            })
    }


    fun getMoreItems() {

        if (!isRequest) {
            isRequest = true
            viewState.visibilityProgressBar(true)
            val disposable = charactersMainUseCase.getMoreItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.addData(it.results)
                    isRequest = false
                    viewState.visibilityProgressBar(false)
                }, {
                    viewState.visibilityProgressBar(false)
                })
        }
    }

    fun get() {
       val disposable = charactersMainUseCase.get().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.populateData(it)
            },{

            })
    }

}