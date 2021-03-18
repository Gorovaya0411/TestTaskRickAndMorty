package com.example.testtaskrickandmorty.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Episode

@Database(entities = [AnswerResults::class, Episode::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerResultDao(): AnswerResultDao
}