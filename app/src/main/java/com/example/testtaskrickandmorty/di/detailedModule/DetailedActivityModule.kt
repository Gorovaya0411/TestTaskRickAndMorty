package com.example.testtaskrickandmorty.di.detailedModule

import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailedActivityModule() {
    @Provides
    fun providerDetailedActivityPresenter():DetailedInfoPresenter{
        return DetailedInfoPresenter()
    }

}