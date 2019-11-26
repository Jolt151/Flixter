package com.example.android.flixter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flixter.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_listing.view.*

class MovieListingAdapter(var movies: List<Movie>) : RecyclerView.Adapter<MovieListingAdapter.MovieListingHolder>() {
    inner class MovieListingHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_listing, parent, false)
        val holder = MovieListingHolder((view))

        holder.itemView.setOnClickListener {
            val intent = Intent(parent.context, DetailActivity::class.java).apply {
                putExtra("movie", movies[holder.adapterPosition])
            }
            parent.context.startActivity(intent)

        }

        return holder
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieListingHolder, position: Int) {
        holder.itemView.title.text = movies[position].title
        holder.itemView.body.text = movies[position].overview

        val url = "https://image.tmdb.org/t/p/w342${movies[position].posterPath}"
        Log.i("adapter", url)
        Picasso.get()
            .load(url)
            .into(holder.itemView.imageView)

    }
}