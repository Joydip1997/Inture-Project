package com.app.inctureproject.data.repository

import com.app.inctureproject.data.model.dto.MovieResult
import com.app.inctureproject.data.model.uimodel.Movie
import com.app.inctureproject.utils.Resource

interface MovieRepository {
    suspend fun getMovies(query : String) : Resource<List<Movie>>
}