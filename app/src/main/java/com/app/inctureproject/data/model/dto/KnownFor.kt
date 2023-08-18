package com.app.inctureproject.data.model.dto

data class KnownFor(
    val cast: List<Cast>,
    val id: String,
    val summary: Summary,
    val title: String,
    val titleType: String,
    val year: Int
)