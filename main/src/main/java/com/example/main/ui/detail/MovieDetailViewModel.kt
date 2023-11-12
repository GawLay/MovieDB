package com.example.main.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.State
import com.example.domain.usecase.MovieDetailUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieDetailUseCase: MovieDetailUseCase) : ViewModel() {

    val movieDetailEvents by lazy {
        MutableSharedFlow<Events>()
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            movieDetailUseCase.getMovieDetail(movieId).collectLatest {
                when (it) {
                    is State.Loading -> {
                        movieDetailEvents.emit(Events.MovieDetailLoading)
                        //show db value
                        //check 1st time
                        val movies = movieDetailUseCase.getLocalMovieDetail(movieId)
                        if (movies != null) {
                            movieDetailEvents.emit(Events.MovieDetailSuccess(movies))
                        }
                    }

                    is State.Success -> {
                        movieDetailEvents.emit(Events.MovieDetailSuccess(it.data))
                        movieDetailUseCase.upsertMovieDetail(it.data)
                    }

                    is State.Failed -> {
                        val movies = movieDetailUseCase.getLocalMovieDetail(movieId)
                        if (movies != null) {
                            movieDetailEvents.emit(Events.MovieDetailSuccess(movies))
                        } else {
                            movieDetailEvents.emit(Events.MovieDetailError)
                        }
                    }
                }
            }
        }
    }

}