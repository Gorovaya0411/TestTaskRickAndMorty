package com.example.testtaskrickandmorty.domain


import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.DataEpisode
import com.example.testtaskrickandmorty.data.repository.CharactersDetailedRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CharactersDetailedUseCase @Inject constructor(private val charactersDetailedRepository: CharactersDetailedRepository) {
    fun getEpisodes(string: String): Observable<List<DataEpisode>> =
        charactersDetailedRepository.getEpisodes(string)

    fun getEpisode(string: String): Observable<DataEpisode> =
        charactersDetailedRepository.getEpisode(string)

    fun get(id: Int): Single<AnswerResults> = charactersDetailedRepository.get(id)
}