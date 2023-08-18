package com.app.inctureproject.di

import com.app.inctureproject.BuildConfig
import com.app.inctureproject.data.ApiKeyInterceptor
import com.app.inctureproject.data.MovieApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : MovieApiInterface {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).client(okHttpClient).build().create(MovieApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)

        return client.build()
    }


}