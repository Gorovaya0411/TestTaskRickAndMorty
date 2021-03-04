package com.example.testtaskrickandmorty.di.detailedModule

import com.example.testtaskrickandmorty.domain.CharactersDetailedUseCase
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailedActivityModule() {
    @DetailedActivityScope
    @Provides
    fun providerDetailedActivityPresenter(charactersDetailedUseCase: CharactersDetailedUseCase):DetailedInfoPresenter{
        return DetailedInfoPresenter(charactersDetailedUseCase)
    }

}