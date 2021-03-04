package com.example.testtaskrickandmorty.di.mainModule

import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Subcomponent


@Subcomponent(modules = [MainActivityModule::class])
@MainActivityScope
interface MainActivitySubComponent {
    val presenter: MainPresenter
}
