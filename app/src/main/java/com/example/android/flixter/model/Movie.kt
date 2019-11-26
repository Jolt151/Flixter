package com.example.android.flixter.model

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Field
import java.io.Serializable

@JsonClass(generateAdapter = true)
@Entity
data class Movie(@PrimaryKey val id: Int, val title: String, val overview: String,
                 @Json(name = "poster_path") val posterPath: String,
                 @Json(name = "vote_average") val rating: Float,
                 val popularity: Double): Serializable

@JsonClass(generateAdapter = true)
data class MovieResults(val results: List<Movie>)