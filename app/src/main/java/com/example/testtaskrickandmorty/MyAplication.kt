package com.example.testtaskrickandmorty

import android.app.AppComponentFactory
import android.app.Application
import com.example.testtaskrickandmorty.di.module.AppComponent
import com.example.testtaskrickandmorty.di.module.AppModule
import com.example.testtaskrickandmorty.di.module.DaggerAppComponent

class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}