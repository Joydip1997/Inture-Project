package com.app.inctureproject.data.repository

import com.app.inctureproject.data.MovieApiInterface
import com.app.inctureproject.data.model.dto.MovieResult
import com.app.inctureproject.data.model.dto.toMovieUiModel
import com.app.inctureproject.data.model.uimodel.Movie
import com.app.inctureproject.utils.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApiInterface) : MovieRepository {

    override suspend fun getMovies(query: String): Resource<List<Movie>> {
        val movieResponse = api.getMovies(query)
        return try {
            if(movieResponse.isSuccessful){
                movieResponse.body()?.movieResults?.let { Resource.Success(it.toMovieUiModel()) } ?: Resource.Error(null,Exception("List Is Empty"))
            }else{
                Resource.Error(null,Exception(movieResponse.errorBody()?.string()))
            }
        }catch (e : Exception){
            Resource.Error(null,e)
        }
    }
}