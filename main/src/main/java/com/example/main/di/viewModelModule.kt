package com.example.main.di

import com.example.main.ui.detail.MovieDetailViewModel
import com.example.main.ui.list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailViewModel)
}