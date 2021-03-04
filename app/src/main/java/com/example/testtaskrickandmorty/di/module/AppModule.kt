package com.example.testtaskrickandmorty.di.module

import com.example.testtaskrickandmorty.data.apiService.RickAndMortyApiService
import com.example.testtaskrickandmorty.data.repository.CharactersDetailedRepository
import com.example.testtaskrickandmorty.data.repository.CharactersMainRepository
import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import com.example.testtaskrickandmorty.domain.CharactersMainUseCase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule
{
    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return  Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()

    }

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit):RickAndMortyApiService = retrofit.create(RickAndMortyApiService::class.java)

    @Singleton
    @Provides
    fun providesMainUseCase(charactersMainRepository: CharactersMainRepository):CharactersMainUseCase = CharactersMainUseCase(charactersMainRepository)

    @Singleton
    @Provides
    fun providesMainRepository(apiService: RickAndMortyApiService):CharactersMainRepository = CharactersMainRepository(apiService)

    @Singleton
    @Provides
    fun providesDetailedUseCase(charactersDetailedRepository: CharactersDetailedRepository):CharactersDetailedUseCase= CharactersDetailedUseCase(charactersDetailedRepository)

    @Singleton
    @Provides
    fun providesDetailedRepository(apiService: RickAndMortyApiService):CharactersDetailedRepository = CharactersDetailedRepository(apiService)
}