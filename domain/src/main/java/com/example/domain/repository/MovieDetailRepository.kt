package com.example.domain.repository

import com.example.domain.model.MovieDetailData
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Flow<State<MovieDetailData>>
    suspend fun updateMovieData(movieDetailData: MovieDetailData)
    suspend fun getLocalMovieDetailData(movieId:Int):MovieDetailData?
}