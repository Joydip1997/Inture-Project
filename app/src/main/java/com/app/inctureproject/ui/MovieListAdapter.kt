package com.app.inctureproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.inctureproject.R
import com.app.inctureproject.data.model.uimodel.Movie
import com.app.inctureproject.databinding.LayoutItemMovieBinding
import com.bumptech.glide.Glide

class MovieListAdapter(private val appContext : Context) : RecyclerView.Adapter<MovieListAdapter.MovieListAdapterViewHolder>() {

    private var _movieList : List<Movie> = emptyList()

    fun setNewMovieList(newMovieList : List<Movie>){
        _movieList = newMovieList
        notifyDataSetChanged() // We should use DiffUtils here but for this assignment i am using it for simplicity
    }

    inner class MovieListAdapterViewHolder(private val binding : LayoutItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.apply {
                Glide.with(appContext).load(movie.imageUrl).placeholder(R.drawable.ic_launcher_foreground).into(ivMovieLogo)
                tvMovieTitle.text = movie.title ?: "No Title"
                tvMovieDescription.text = movie.description ?: "No Description"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MovieListAdapterViewHolder (
        LayoutItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = _movieList.size

    override fun onBindViewHolder(holder: MovieListAdapterViewHolder, position: Int) {
        _movieList[position].run {
            holder.bind(this)
        }
    }
}