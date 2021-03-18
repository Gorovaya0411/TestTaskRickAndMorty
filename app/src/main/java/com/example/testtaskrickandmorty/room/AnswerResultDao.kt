package com.example.testtaskrickandmorty.room

import androidx.room.*
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Episode


@Dao
interface AnswerResultDao {
    @Query("SELECT * FROM AnswerResults")
    fun getAll(): List<AnswerResults>

    @Query("SELECT * FROM AnswerResults WHERE id = :id")
    fun getById(id: Int): AnswerResults

    @Query("SELECT * FROM Episode WHERE id = :id")
    fun getByIdEpisode(id: Int): Episode

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(kist: AnswerResults)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisodes(kist: Episode)

    @Delete
    fun delete(employee: AnswerResults?)

}