package com.example.testtaskrickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testtaskrickandmorty.room.EpisodeConverter
import java.io.Serializable

@Entity
class AnswerResults() : Serializable {
    @PrimaryKey
    var id: Int? = null
    var name: String= ""
    var image: String= ""
    var status: String= ""
    var species: String= ""
    var type: String= ""
    var gender: String= ""
    var created: String= ""

    @TypeConverters(EpisodeConverter::class)
    var episode: List<String>? = null
    var episodeCharacters: String= ""
    @TypeConverters(EpisodeConverter::class)
    var episodesCharacters: List<String>? = null


}

