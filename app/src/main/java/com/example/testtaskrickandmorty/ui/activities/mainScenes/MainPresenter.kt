package com.example.testtaskrickandmorty.ui.activities.mainScenes


import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.domain.CharactersMainUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val charactersMainUseCase: CharactersMainUseCase) :
    MvpPresenter<MainView>() {

    private var isRequest: Boolean = false
    fun swipeRefresh() {

        val disposable = charactersMainUseCase.swipeRefresh()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                viewState.showInternet()
                viewState.populateData(it.results)
            }, {
//                viewState.showNoInternet()
//                viewState.lackInternet()
                viewState.populateData(viewState.throwable())
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
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

}