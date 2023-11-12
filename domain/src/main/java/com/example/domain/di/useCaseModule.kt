package com.example.domain.di

import org.koin.dsl.module

val useCaseModule = module {
    includes(movieListUseCaseModule,movieDetailUseCaseModule)
}