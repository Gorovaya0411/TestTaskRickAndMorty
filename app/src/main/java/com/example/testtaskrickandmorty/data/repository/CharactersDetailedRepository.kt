package com.example.testtaskrickandmorty.data.repository

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.DataEpisode
import com.example.testtaskrickandmorty.data.model.Episode
import com.example.testtaskrickandmorty.room.AnswerResultDao
import com.example.testtaskrickandmorty.room.App
import com.example.testtaskrickandmorty.room.AppDatabase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersDetailedRepository @Inject constructor(private val apiService: RickAndMortyApiService) {
    private val db: AppDatabase = App.getDatabase()
    var employeeDao: AnswerResultDao = db.answerResultDao()
    fun getEpisodes(string: String): Observable<List<DataEpisode>> {
        return apiService.getEpisodes(string).subscribeOn(Schedulers.io())
    }

    fun getEpisode(string: String): Observable<DataEpisode> {
        return apiService.getEpisode(string).subscribeOn(Schedulers.io())
    }

    fun getCharactersByID(id: Int): Single<AnswerResults> {
        return Single.fromCallable {
            return@fromCallable employeeDao.getById(id)
        }
    }

    fun insertEpisodesInEpisode(episode: Episode): Single<Unit> {
        return Single.fromCallable {
            return@fromCallable employeeDao.insertEpisodes(episode)
        }

    }

    fun getInfoByEpisode(id: Int): Single<Episode> {
        return Single.fromCallable {
            return@fromCallable employeeDao.getByIdEpisode(id)
        }
    }

}