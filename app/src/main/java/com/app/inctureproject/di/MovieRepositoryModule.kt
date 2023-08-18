package com.app.inctureproject.di

import com.app.inctureproject.data.MovieApiInterface
import com.app.inctureproject.data.repository.MovieRepository
import com.app.inctureproject.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {


    @Provides
    @Singleton
    fun provideMovieRepositoryModule(apiInterface: MovieApiInterface) : MovieRepository = MovieRepositoryImpl(apiInterface)

}