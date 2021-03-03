package com.example.testtaskrickandmorty.data.model

import java.io.Serializable

data class AnswerResults(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val episode: List<String>,
    val created: String
) : Serializable