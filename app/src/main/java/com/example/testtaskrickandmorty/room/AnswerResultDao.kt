package com.example.testtaskrickandmorty.room

import androidx.room.*
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Data
import io.reactivex.Flowable


@Dao
interface AnswerResultDao {
        @Query("SELECT * FROM AnswerResults")
        fun getAll():Flowable<List<AnswerResults>>
        @Query("SELECT * FROM AnswerResults WHERE id = :id")
        fun getById(id: Int):AnswerResults

        @Insert
        fun insert(employee: Data)

        @Update
        fun update(employee: AnswerResults?)

        @Delete
        fun delete(employee:AnswerResults?)

}