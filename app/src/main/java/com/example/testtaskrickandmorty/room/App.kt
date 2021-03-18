package com.example.testtaskrickandmorty.room

import android.app.Application
import androidx.room.Room
import com.example.testtaskrickandmorty.di.module.AppComponent
import com.example.testtaskrickandmorty.di.module.AppModule
import com.example.testtaskrickandmorty.di.module.DaggerAppComponent


class App : Application() {
    companion object {
        private lateinit var database: AppDatabase
        lateinit var appComponent: AppComponent

        @JvmStatic
        fun getDatabase(): AppDatabase {
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "ffff").build()
    }
}
