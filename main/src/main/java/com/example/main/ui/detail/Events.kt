package com.example.main.ui.detail

import com.example.domain.model.MovieDetailData

sealed class Events {
    object MovieDetailLoading : Events()
    object MovieDetailError : Events()
    class MovieDetailSuccess(val movieDetailData: MovieDetailData) : Events()
}