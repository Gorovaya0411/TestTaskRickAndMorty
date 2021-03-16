package com.example.testtaskrickandmorty.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors

open class EpisodeConverter {

    @TypeConverter
    fun get_string(str:List<String>):String {
        val pictures = StringBuilder()
        for (s in str) pictures.append(s).append(",")
        return pictures.toString()
    }
    @TypeConverter
    fun set_string(str:String): List<String>{
        return str.split(",")
    }

        }