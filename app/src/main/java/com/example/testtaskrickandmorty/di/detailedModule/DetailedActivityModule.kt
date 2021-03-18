package com.example.testtaskrickandmorty.di.detailedModule

import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoPresenter
import dagger.Module

@Module
class DetailedActivityModule() {
    @DetailedActivityScope
    fun providerDetailedActivityPresenter(charactersDetailedUseCase: CharactersDetailedUseCase): DetailedInfoPresenter {
        return DetailedInfoPresenter(charactersDetailedUseCase)
    }

}