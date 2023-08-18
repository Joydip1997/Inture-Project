package com.app.inctureproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    val query: String,
    @SerializedName("results")
    val movieResults: List<MovieResult>,
    val types: List<String>
)