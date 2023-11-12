package com.example.data.di

import com.example.data.repository.MovieListRepositoryImpl
import com.example.domain.repository.MovieListRepository
import org.koin.dsl.module

val movieListModule = module {

    single<MovieListRepository> { MovieListRepositoryImpl(get(),get(),get()) }
}