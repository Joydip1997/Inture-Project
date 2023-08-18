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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieList : MutableLiveData<List<Movie>> = MutableLiveData()
    val movieList : LiveData<List<Movie>> = _movieList

    private val _uiState : MutableSharedFlow<UiStates> = MutableSharedFlow()
    val uiState : SharedFlow<UiStates> = _uiState.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _uiState.emit(UiStates.Error(throwable.message))
        }
    }

    fun onMovieSearch(query : String){
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO){
                _uiState.emit(UiStates.Loading)
                when(val state = movieRepository.getMovies(query)){
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
}