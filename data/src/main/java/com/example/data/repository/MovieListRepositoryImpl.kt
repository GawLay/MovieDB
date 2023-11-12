package com.example.data.repository

import com.example.data.local.PopularMovieDao
import com.example.data.local.UpcomingMovieDao
import com.example.data.remote.IApiHelper
import com.example.domain.model.PopularMovie
import com.example.domain.model.PopularMovieListResponse
import com.example.domain.model.UpcomingMovie
import com.example.domain.model.UpcomingMovieListResponse
import com.example.domain.repository.MovieListRepository
import com.example.domain.repository.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import retrofit2.HttpException

class MovieListRepositoryImpl(
    private val apiHelperImpl: IApiHelper,
    private val popularMovieDao: PopularMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao
) : MovieListRepository, KoinComponent {

    override suspend fun getPopularMovieList(): Flow<State<PopularMovieListResponse>> = flow {
        emit(State.loading())
        try {
            val data = apiHelperImpl.getPopularMovies()
            emit(State.Success(data))
        } catch (e: HttpException) {
            emit(State.failed(e.message, e.code()))
        } catch (e: Exception) {
            emit(State.failed(e.message ?: "Something went wrong", null))
        }
    }

    override suspend fun getUpcomingList(): Flow<State<UpcomingMovieListResponse>> = flow {
        emit(State.loading())
        try {
            val data = apiHelperImpl.getUpcomingMovies()
            emit(State.Success(data))
        } catch (e: HttpException) {
            emit(State.failed(e.message, e.code()))
        } catch (e: Exception) {
            emit(State.failed(e.message ?: "Something went wrong", null))
        }
    }

    override suspend fun getLocalPopularList(): Flow<List<PopularMovie>> {
        return popularMovieDao.getAllMovieList()
    }

    override suspend fun getLocalUpcomingList(): Flow<List<UpcomingMovie>> {
        return upcomingMovieDao.getAllMovieList()
    }

    override suspend fun updateLocalPopularList(movies: List<PopularMovie>) {
        popularMovieDao.updateMovies(movies)
    }

    override suspend fun updateLocalUpcomingList(movies: List<UpcomingMovie>) {
       upcomingMovieDao.updateMovies(movies)
    }


}