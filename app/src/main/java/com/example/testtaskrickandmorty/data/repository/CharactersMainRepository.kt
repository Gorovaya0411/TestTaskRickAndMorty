package com.example.testtaskrickandmorty.data.repository

import androidx.room.Insert
import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.room.AnswerResultDao
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.room.AppDatabase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersMainRepository @Inject constructor(private val apiService: RickAndMortyApiService) {
    private var increment: Int = 1
    private val db: AppDatabase = App.getDatabase()
    var employeeDao: AnswerResultDao = db.answerResultDao()
    fun swipeRefresh(): Observable<Data> {
        increment = 1
        return apiService.getCharacter(page = increment).flatMap { it ->
            it.results.forEach {
                employeeDao.insertAll(it)
            }
            return@flatMap Observable.just(it)
        }.subscribeOn(Schedulers.io())
    }

    fun getMoreItems(): Observable<Data> {
        increment += 1

        return apiService.getCharacter(page = increment).flatMap { data ->

            val listEpisode1 = arrayListOf<AnswerResults>()
            data.results.forEach {
                employeeDao.insertAll(it)

            }
            return@flatMap Observable.just(data)
        }.subscribeOn(Schedulers.io())
    }

    fun get(): Single<List<AnswerResults>> {
        return Single.fromCallable {
            return@fromCallable employeeDao.getAll()
        }
    }
}