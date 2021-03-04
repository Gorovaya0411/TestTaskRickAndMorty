package com.example.testtaskrickandmorty.domain

import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.data.repository.CharactersMainRepository
import io.reactivex.Observable
import javax.inject.Inject

class CharactersMainUseCase @Inject constructor(private val charactersMainRepository: CharactersMainRepository) {
    fun swipeRefresh(): Observable<Data> = charactersMainRepository.swipeRefresh()
    fun getMoreItems(): Observable<Data> = charactersMainRepository.getMoreItems()
}