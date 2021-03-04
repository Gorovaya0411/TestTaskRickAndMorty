package com.example.testtaskrickandmorty.domain

import com.example.testtaskrickandmorty.data.model.DataEpisode
import com.example.testtaskrickandmorty.data.repository.CharactersDetailedRepository
import io.reactivex.Observable
import javax.inject.Inject

class CharactersDetailedUseCase @Inject constructor(private val charactersDetailedRepository: CharactersDetailedRepository) {
    fun getEpisodes(string: String): Observable<List<DataEpisode>> =
        charactersDetailedRepository.getEpisodes(string)

    fun getEpisode(string: String): Observable<DataEpisode> =
        charactersDetailedRepository.getEpisode(string)
}