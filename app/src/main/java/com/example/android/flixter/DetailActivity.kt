package com.example.android.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android.flixter.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = findViewById(R.id.title)

        val movie = intent.getSerializableExtra("movie") as Movie
        ratingBar.rating = movie.rating
        title.text = movie.title
        body.text = movie.overview

    }
}
