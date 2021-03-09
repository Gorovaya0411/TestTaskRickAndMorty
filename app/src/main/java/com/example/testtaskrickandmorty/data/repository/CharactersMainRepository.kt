package com.example.testtaskrickandmorty.data.repository

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.model.Data
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersMainRepository @Inject constructor(private val apiService: RickAndMortyApiService) {
    private var increment: Int = 1
    fun swipeRefresh(): Observable<Data> {
        increment = 1
        return apiService.getCharacter(page = increment).subscribeOn(Schedulers.io())
    }

    fun getMoreItems(): Observable<Data> {
        increment += 1
        return apiService.getCharacter(page = increment).subscribeOn(Schedulers.io())
    }
}