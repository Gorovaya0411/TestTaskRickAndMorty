package com.example.testtaskrickandmorty.data.repository

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.room.AnswerResultDao
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.room.AppDatabase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersMainRepository @Inject constructor(private val apiService: RickAndMortyApiService) {
    private var increment: Int = 1
    private val db: AppDatabase = App.getDatabase()
    var employeeDao: AnswerResultDao = db.answerResultDao()
    fun swipeRefresh(): Observable<Data> {
        increment = 1
        return apiService.getCharacter(page = increment).flatMap { data ->
//            val listEpisode = arrayListOf<AnswerResults>()


//            data.results.forEach {
//                listEpisode.add(it)
//                employee.id = it.id
//                employee.name = it.name
//                employee.created = it.created
//                employee.episode = it.episode
//                employee.gender = it.gender
//                employee.type = it.type
//                employee.status = it.status
//                employee.species = it.species
//                employee.image = it.image
//            }
            employeeDao.insert(data)
            return@flatMap Observable.just(data)
        }.subscribeOn(Schedulers.io())
    }

    fun getMoreItems(): Observable<Data> {
        increment += 1

        return apiService.getCharacter(page = increment).flatMap { data ->

            val listEpisode1 = arrayListOf<AnswerResults>()

            employeeDao.insert(data)
            return@flatMap Observable.just(data)
        }.subscribeOn(Schedulers.io())
    }
}