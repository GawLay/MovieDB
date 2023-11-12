package com.example.data.di

import androidx.room.Room
import com.example.data.local.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "MovieDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
       val database=  get<MovieDatabase>()
        database.popularMovieDao()
    }
    single {
        val database=  get<MovieDatabase>()
        database.upcomingDataDao()
    }
    single {
        val database=  get<MovieDatabase>()
        database.movieDetailDao()
    }
}