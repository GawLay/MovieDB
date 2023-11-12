package com.example.domain.usecase

import com.example.domain.model.PopularMovie
import com.example.domain.model.UpcomingMovie
import com.example.domain.repository.MovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class MovieListUseCase(private val movieListRepository: MovieListRepository) {
    suspend fun getPopularList()=
        movieListRepository.getPopularMovieList()

    suspend fun getUpcomingList()=
        movieListRepository.getUpcomingList()

    suspend fun getLocalPopularList()=
        movieListRepository.getLocalPopularList().flowOn(Dispatchers.IO)
    suspend fun getLocalUpcomingList()=
        movieListRepository.getLocalUpcomingList().flowOn(Dispatchers.IO)

    suspend fun updatePopularList(popularMovie:List<PopularMovie>){
        movieListRepository.updateLocalPopularList(popularMovie)
    }

    suspend fun updateUpcomingList(upcomingMovie:List<UpcomingMovie>){
        movieListRepository.updateLocalUpcomingList(upcomingMovie)
    }
}