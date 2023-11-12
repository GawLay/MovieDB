package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.model.MovieDetailData
import com.example.domain.model.PopularMovie
import com.example.domain.model.UpcomingMovie

@Database(entities = [PopularMovie::class, UpcomingMovie::class,MovieDetailData::class], version = 3, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun upcomingDataDao():UpcomingMovieDao
    abstract fun movieDetailDao():MovieDetailDao
}