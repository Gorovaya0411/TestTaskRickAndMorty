package com.example.testtaskrickandmorty.di.module

import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivityModule
import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoActivity
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainActivity
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Component

@Component(modules = [AppModule::class,DetailedActivityModule::class,MainActivityModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun injectDetailedActivityModule(detailedActivity:DetailedInfoActivity)
    fun injectMainActivityModule(mainActivity: MainActivity)
}