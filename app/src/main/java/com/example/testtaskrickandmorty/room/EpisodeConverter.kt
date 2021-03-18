package com.example.testtaskrickandmorty.room

import androidx.room.TypeConverter

open class EpisodeConverter {

    @TypeConverter
    fun get_string(str: List<String>?): String {
        val pictures = StringBuilder()
        if (str == null) {
            pictures.append("")
        } else {
            for (s in str) pictures.append(s).append(",")
        }

        return pictures.toString()
    }

    @TypeConverter
    fun set_string(str: String): List<String> {
        return str.split(",")
    }

}