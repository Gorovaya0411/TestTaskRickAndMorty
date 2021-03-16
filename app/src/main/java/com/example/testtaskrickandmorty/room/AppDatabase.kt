package com.example.testtaskrickandmorty.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtaskrickandmorty.data.model.AnswerResults

@Database(entities = [AnswerResults::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
//    abstract var answerResultDao: AnswerResultDao
    abstract fun answerResultDao():AnswerResultDao
}