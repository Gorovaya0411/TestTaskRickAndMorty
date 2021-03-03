package com.example.testtaskrickandmorty.di.detailedModule

import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoPresenter
import dagger.Module

@Module
class DetailedActivityModule() {
    fun providerDetailedActivityPresenter():DetailedInfoPresenter{
        return DetailedInfoPresenter()
    }

}