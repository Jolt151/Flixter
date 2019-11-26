package com.example.android.flixter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.flixter.api.MovieService
import com.example.android.flixter.model.Movie
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.internal.tls.OkHostnameVerifier
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    private val API_KEY = "4d5d29dd23521733f98f8146903d11d1"

    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val url = it.request().url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = it.request().newBuilder()
                .url(url).build()

            Log.i("VM", url.toString())

            it.proceed(request)
        }
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
    private val api = retrofit.create(MovieService::class.java)

    init {
        viewModelScope.launch {
            val newMovies = api.getMovies().results
            movies.value = newMovies
            Log.i(this@MainViewModel.javaClass.name, movies.toString())
        }
    }

    suspend fun getMoreMovies(page: Int) = viewModelScope.launch {
        val newMovies = api.getMovieByPage(page).results
        val allMovies = mutableListOf<Movie>()
        movies.value?.let {
            allMovies.addAll(it)
        }
        allMovies.addAll(newMovies)
        movies.value = allMovies

    }
}