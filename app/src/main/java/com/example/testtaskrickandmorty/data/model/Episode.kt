package com.example.testtaskrickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testtaskrickandmorty.room.EpisodeConverter

@Entity
class Episode {
    @PrimaryKey
    var id: Int? = null
    var nameEpisodeCharacters: String = ""

    @TypeConverters(EpisodeConverter::class)
    var nameEpisodesCharacters: List<String>? = null
}