package com.example.testtaskrickandmorty.domain


import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.DataEpisode
import com.example.testtaskrickandmorty.data.model.Episode
import com.example.testtaskrickandmorty.data.repository.CharactersDetailedRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CharactersDetailedUseCase @Inject constructor(private val charactersDetailedRepository: CharactersDetailedRepository) {
    fun getEpisodes(string: String): Observable<List<DataEpisode>> =
        charactersDetailedRepository.getEpisodes(string)

    fun getEpisode(string: String): Observable<DataEpisode> =
        charactersDetailedRepository.getEpisode(string)

    fun getCharactersByID(id: Int): Single<AnswerResults> =
        charactersDetailedRepository.getCharactersByID(id)

    fun insertEpisodesInEpisode(episode: Episode): Single<Unit> =
        charactersDetailedRepository.insertEpisodesInEpisode(episode)

    fun getInfoByEpisode(id: Int): Single<Episode> =
        charactersDetailedRepository.getInfoByEpisode(id)
}