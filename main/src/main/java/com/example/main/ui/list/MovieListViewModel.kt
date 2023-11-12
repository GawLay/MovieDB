package com.example.main.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.State
import com.example.domain.usecase.MovieListUseCase
import com.example.utility.constant.showLog
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieListUseCase: MovieListUseCase) : ViewModel() {


    //    suspend fun getUpcomingList() = movieListUseCase.getUpcomingList()
     val moviesListEvent by lazy {
        MutableSharedFlow<Events>()
    }


    fun getPopularList() {
        viewModelScope.launch {
            movieListUseCase.getPopularList().collectLatest {
                when (it) {
                    is State.Loading -> {
                        moviesListEvent.emit(Events.PopularListLoading)
                        //show db value
                        //check 1st time
                        movieListUseCase.getLocalPopularList().collectLatest { movies ->
                            if (movies.isNotEmpty()) {
                                moviesListEvent.emit(Events.PopularListSuccess(movies))
                            }
                        }
                    }

                    is State.Success -> {
                        moviesListEvent.emit(Events.PopularListSuccess(it.data.results))
                        movieListUseCase.updatePopularList(it.data.results)
                    }

                    is State.Failed -> {
                        //show db value if exist
                        movieListUseCase.getLocalPopularList().collectLatest { movies ->
                            if (movies.isNotEmpty()) {
                                moviesListEvent.emit(Events.PopularListSuccess(movies))
                            }else{
                                moviesListEvent.emit(Events.PopularListError)
                            }
                        }
                    }
                }
            }
        }
    }

    fun getUpcomingList() {
        viewModelScope.launch {
            movieListUseCase.getUpcomingList().collectLatest {
                when (it) {
                    is State.Loading -> {
                        moviesListEvent.emit(Events.PopularListLoading)
                        //show db value
                        //check 1st time
                        movieListUseCase.getLocalUpcomingList().collectLatest { movies ->
                            if (movies.isNotEmpty()) {
                                moviesListEvent.emit(Events.UpcomingListSuccess(movies))
                            }
                        }
                    }

                    is State.Success -> {
                        moviesListEvent.emit(Events.UpcomingListSuccess(it.data.results))
                        movieListUseCase.updateUpcomingList(it.data.results)
                    }

                    is State.Failed -> {
                        //show db value if exist
                        movieListUseCase.getLocalUpcomingList().collectLatest { movies ->
                            if (movies.isNotEmpty()) {
                                moviesListEvent.emit(Events.UpcomingListSuccess(movies))
                            }else{
                                moviesListEvent.emit(Events.UpcomingListError)
                            }
                        }
                    }
                }
            }
        }
    }
}