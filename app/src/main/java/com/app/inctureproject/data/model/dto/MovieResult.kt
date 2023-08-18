package com.app.inctureproject.data.model.dto

import com.app.inctureproject.data.model.uimodel.Movie

data class MovieResult(
    val akas: List<String>,
    val id: String,
    val image: Image?,
    val knownFor: List<KnownFor>,
    val legacyNameText: String,
    val name: String,
    val nextEpisode: String,
    val numberOfEpisodes: Int,
    val principals: List<Principal>,
    val runningTimeInMinutes: Int,
    val seriesEndYear: Int,
    val seriesStartYear: Int,
    val title: String,
    val titleType: String,
    val year: Int
)

fun List<MovieResult>.toMovieUiModel() = map {
    Movie(
        id = it.id,
        title = it.title,
        description = it.numberOfEpisodes.toString(),
        imageUrl = it.image?.url
    )
}