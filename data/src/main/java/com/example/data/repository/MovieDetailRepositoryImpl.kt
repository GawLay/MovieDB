package com.example.data.repository

import com.example.data.local.MovieDetailDao
import com.example.data.remote.IApiHelper
import com.example.domain.model.MovieDetailData
import com.example.domain.repository.MovieDetailRepository
import com.example.domain.repository.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieDetailRepositoryImpl(
    private val iApiHelper: IApiHelper,
    private val movieDetailDao: MovieDetailDao
) : MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Flow<State<MovieDetailData>> = flow {
        emit(State.loading())
        try {
            val data = iApiHelper.getMovieDetail(movieId = movieId)
            emit(State.Success(data))
        } catch (e: HttpException) {
            emit(State.failed(e.message, e.code()))
        } catch (e: Exception) {
            emit(State.failed(e.message ?: "Something went wrong", null))
        }
    }

    override suspend fun updateMovieData(movieDetailData: MovieDetailData) {
        movieDetailDao.insertAll(movieDetailData)
    }

    override suspend fun getLocalMovieDetailData(movieId: Int): MovieDetailData?{
        return movieDetailDao.getMovieDetail(movieId)
    }
}