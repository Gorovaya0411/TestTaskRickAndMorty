package com.example.testtaskrickandmorty.data.repository

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.data.model.DataEpisode
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailedRepository @Inject constructor(private val apiService: RickAndMortyApiService) {
    fun getEpisodes(string: String): Observable<List<DataEpisode>> {
        return apiService.getEpisodes(string).subscribeOn(Schedulers.io())
    }

    fun getEpisode(string: String): Observable<DataEpisode> {
        return apiService.getEpisode(string).subscribeOn(Schedulers.io())
    }
}