package com.example.domain.di

import com.example.domain.usecase.MovieDetailUseCase
import org.koin.dsl.module

val movieDetailUseCaseModule = module {
    single { MovieDetailUseCase(get()) }
}