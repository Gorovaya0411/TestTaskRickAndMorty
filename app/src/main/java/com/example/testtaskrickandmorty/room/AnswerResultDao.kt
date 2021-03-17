package com.example.testtaskrickandmorty.room

import androidx.room.*
import com.example.testtaskrickandmorty.data.model.AnswerResults
import com.example.testtaskrickandmorty.data.model.Data
import com.example.testtaskrickandmorty.data.model.DataEpisode
import io.reactivex.Flowable


@Dao
interface AnswerResultDao {
        @Query("SELECT * FROM AnswerResults")
        fun getAll():List<AnswerResults>
        @Query("SELECT * FROM AnswerResults WHERE id = :id")
        fun getById(id: Int):AnswerResults

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        @JvmSuppressWildcards
        fun insertAll(kist: AnswerResults)

        @Query("UPDATE AnswerResults SET episodesCharacters = :newSalary WHERE id IN (:idList)")
        fun updateEpisodes(idList:Int, newSalary:List<String>)

        @Query("UPDATE AnswerResults SET episodesCharacters = :newSalary WHERE id IN (:idList)")
        fun updateEpisode(idList:Int, newSalary:String)

        @Delete
        fun delete(employee:AnswerResults?)

}