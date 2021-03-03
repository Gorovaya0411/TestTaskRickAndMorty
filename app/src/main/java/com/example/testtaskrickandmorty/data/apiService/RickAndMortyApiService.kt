package com.example.testtaskrickandmorty.data.apiService

import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.data.model.DataEpisode
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character/")
    fun getCharacter(
        @Query("page") page: Int
    ): Observable<Data>

    @GET("episode/{episode}")
    fun getEpisodes(
        @Path("episode") episode: String
    ): Observable<List<DataEpisode>>

    @GET("episode/{episode}")
    fun getEpisode(
        @Path("episode") episode: String
    ): Observable<DataEpisode>

    companion object Factory {
        fun create(): RickAndMortyApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/api/")
                .build()

            return retrofit.create(RickAndMortyApiService::class.java)
        }
    }
}