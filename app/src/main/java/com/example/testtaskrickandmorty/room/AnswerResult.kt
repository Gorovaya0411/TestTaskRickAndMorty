//package com.example.testtaskrickandmorty.room
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.TypeConverters
//import kotlin.properties.Delegates
//
//@Entity
//class AnswerResult {
//    @PrimaryKey
//    var id: Int = 0
//    var name: String = ""
//    var image: String = ""
//    var status: String = ""
//    var species: String = ""
//    var type: String = ""
//    var gender: String = ""
//    var created: String = ""
//    var episode: String = ""
//
//    @TypeConverters(EpisodeConverter::class)
//    lateinit var episodes: List<String>
//
//}