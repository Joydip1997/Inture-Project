package com.app.inctureproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.inctureproject.data.model.dto.MovieResult
import com.app.inctureproject.data.model.uimodel.Movie
import com.app.inctureproject.data.repository.MovieRepository
import com.app.inctureproject.utils.Resource
import com.app.inctureproject.utils.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieList : MutableLiveData<List<Movie>> = MutableLiveData()
    val movieList : LiveData<List<Movie>> = _movieList

    private val _uiState : MutableSharedFlow<UiStates> = MutableSharedFlow()
    val uiState : SharedFlow<UiStates> = _uiState.asSharedFlow()

    fun onMovieSearch(query : String){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiStates.Loading)
            val state = movieRepository.getMovies(query)
            when(state){
                is Resource.Error -> {
                    _uiState.emit(UiStates.Error(state.error?.message))
                }
                is Resource.Success -> {
                    _movieList.postValue(state.data!!)
                    _uiState.emit(UiStates.Complete)
                }
            }
        }
    }
}