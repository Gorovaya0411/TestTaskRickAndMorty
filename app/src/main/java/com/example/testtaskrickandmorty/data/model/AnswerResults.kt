package com.example.testtaskrickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testtaskrickandmorty.room.EpisodeConverter
import java.io.Serializable
@Entity
data class AnswerResults(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val created: String,
    @TypeConverters(EpisodeConverter::class)
    val episode: List<String>

) : Serializable