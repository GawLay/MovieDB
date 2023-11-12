package com.example.data.di

import com.example.data.repository.MovieDetailRepositoryImpl
import com.example.domain.repository.MovieDetailRepository
import org.koin.dsl.module

val movieDetailRepoModule = module {

    single<MovieDetailRepository> { MovieDetailRepositoryImpl(get(),get()) }
}