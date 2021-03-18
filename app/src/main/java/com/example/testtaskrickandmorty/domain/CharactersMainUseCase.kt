package com.example.testtaskrickandmorty.domain

import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.data.repository.CharactersMainRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CharactersMainUseCase @Inject constructor(private val charactersMainRepository: CharactersMainRepository) {
    fun swipeRefresh(): Observable<Data> = charactersMainRepository.swipeRefresh()
    fun getMoreItems(): Observable<Data> = charactersMainRepository.getMoreItems()
    fun getAllCharacters(): Single<List<AnswerResults>> =
        charactersMainRepository.getAllCharacters()
}