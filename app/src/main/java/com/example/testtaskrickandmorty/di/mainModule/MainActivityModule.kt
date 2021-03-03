package com.example.testtaskrickandmorty.di.mainModule

import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun providesMainActivityPresenter(): MainPresenter {
        return MainPresenter()

    }
}