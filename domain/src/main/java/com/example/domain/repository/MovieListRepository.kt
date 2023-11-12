package com.example.domain.repository

import com.example.domain.model.PopularMovie
import com.example.domain.model.PopularMovieListResponse
import com.example.domain.model.UpcomingMovie
import com.example.domain.model.UpcomingMovieListResponse
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getPopularMovieList(): Flow<State<PopularMovieListResponse>>
    suspend fun getUpcomingList(): Flow<State<UpcomingMovieListResponse>>
    suspend fun getLocalPopularList():Flow<List<PopularMovie>>
    suspend fun getLocalUpcomingList():Flow<List<UpcomingMovie>>
    suspend fun updateLocalPopularList(movies:List<PopularMovie>)
    suspend fun updateLocalUpcomingList(movies:List<UpcomingMovie>)
}