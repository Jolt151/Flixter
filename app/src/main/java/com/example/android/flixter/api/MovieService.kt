package com.example.android.flixter.api

import android.media.Image
import com.example.android.flixter.model.Movie
import com.example.android.flixter.model.MovieResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getMovies(): MovieResults

    @GET("movie/now_playing")
    suspend fun getMovieByPage(@Query("page") page: Int): MovieResults
}