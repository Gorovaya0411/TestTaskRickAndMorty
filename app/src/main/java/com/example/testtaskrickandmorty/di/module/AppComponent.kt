package com.example.testtaskrickandmorty.di.module

import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivityModule
import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivityScope
import com.example.testtaskrickandmorty.di.detailedModule.DetailedActivitySubComponent
import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.di.mainModule.MainActivitySubComponent
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoActivity
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainActivity
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Component
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivityModule: MainActivityModule): MainActivitySubComponent
    fun inject(detailedActivityModule: DetailedActivityModule): DetailedActivitySubComponent
}