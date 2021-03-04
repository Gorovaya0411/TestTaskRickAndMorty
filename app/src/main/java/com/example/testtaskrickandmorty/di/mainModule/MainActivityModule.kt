package com.example.testtaskrickandmorty.di.mainModule

import com.example.testtaskrickandmorty.di.module.AppScope
import com.example.testtaskrickandmorty.domain.CharactersMainUseCase
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @MainActivityScope
    @AppScope
    fun providesMainActivityPresenter(charactersMainUseCase: CharactersMainUseCase): MainPresenter {
        return MainPresenter(charactersMainUseCase)

    }
}