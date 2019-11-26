package com.example.android.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flixter.model.Movie
import com.example.android.flixter.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieListingAdapter(listOf())
        recyclerView.adapter = adapter


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.movies.observe(this, Observer {
            updateUI(it)
        })
    }

    fun updateUI(movies: List<Movie>) {
        adapter.movies = movies
        adapter.notifyDataSetChanged()
    }
}
