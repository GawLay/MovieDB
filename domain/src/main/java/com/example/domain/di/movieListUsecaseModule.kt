package com.example.domain.di

import com.example.domain.usecase.MovieListUseCase
import org.koin.dsl.module

val movieListUseCaseModule = module {
    single { MovieListUseCase(get()) }
}