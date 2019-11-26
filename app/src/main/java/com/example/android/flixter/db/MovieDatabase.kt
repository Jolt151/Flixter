package com.example.android.flixter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.flixter.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}