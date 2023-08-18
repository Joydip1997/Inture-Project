package com.app.inctureproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.inctureproject.databinding.ActivityMainBinding
import com.app.inctureproject.ui.MovieListAdapter
import com.app.inctureproject.utils.UiStates
import com.app.inctureproject.utils.collectIn
import com.app.inctureproject.utils.hideKeyboard
import com.app.inctureproject.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get<MainViewModel>(MainViewModel::class.java)
        movieListAdapter = MovieListAdapter(this)
        binding.apply {
            btnSearch.setOnClickListener {
                val query = binding.etMovieName.text.toString()
                if(query.isEmpty()){
                    this@MainActivity.toast("Please enter something to search")
                    return@setOnClickListener
                }
                hideKeyboard()
                viewModel.onMovieSearch(query)
            }
            rvMovies.apply {
                layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                adapter = movieListAdapter
            }
        }

        viewModel.apply {
            movieList.observe(this@MainActivity){
                movieListAdapter.setNewMovieList(it)
            }
            uiState.collectIn(this@MainActivity){
                binding.apply {
                    rvMovies.isVisible = it is UiStates.Complete
                    cpProgress.isVisible = it is UiStates.Loading
                }
                when(it){
                    is UiStates.Error -> {
                        this@MainActivity.toast(it.message)
                    }
                    else -> Unit
                }
            }
        }
    }
}