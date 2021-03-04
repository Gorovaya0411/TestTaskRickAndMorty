package com.example.testtaskrickandmorty.di.detailedModule

import com.example.testtaskrickandmorty.di.mainModule.MainActivityModule
import com.example.testtaskrickandmorty.di.mainModule.MainActivityScope
import com.example.testtaskrickandmorty.ui.activities.detailedInfoScenes.DetailedInfoPresenter
import com.example.testtaskrickandmorty.ui.activities.mainScenes.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [DetailedActivityModule::class])
@MainActivityScope
interface DetailedActivitySubComponent {
    val presenter: DetailedInfoPresenter
}
