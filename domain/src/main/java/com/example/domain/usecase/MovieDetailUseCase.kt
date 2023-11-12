package com.example.domain.usecase

import com.example.domain.model.MovieDetailData
import com.example.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MovieDetailUseCase(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun getLocalMovieDetail(movieId: Int) =
        movieDetailRepository.getLocalMovieDetailData(movieId)

    suspend fun upsertMovieDetail(movieDetailData: MovieDetailData) {
        movieDetailRepository.updateMovieData(movieDetailData)
    }

    suspend fun getMovieDetail(movieId: Int) =
        movieDetailRepository.getMovieDetail(movieId).flowOn(Dispatchers.IO)
}