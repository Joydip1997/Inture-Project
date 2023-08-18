package com.app.inctureproject.data

import com.app.inctureproject.data.model.dto.MovieResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("/title/find")
    suspend fun getMovies(
        @Query("q") query : String
    ) : Response<MovieResponseDTO>
}