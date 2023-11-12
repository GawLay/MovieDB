package com.example.main.ui.list

import com.example.domain.model.PopularMovie
import com.example.domain.model.UpcomingMovie

sealed class Events {
    object PopularListLoading:Events()
    object PopularListError:Events()
    class PopularListSuccess(val popularMovie:List<PopularMovie>):Events()

    object UpcomingListLoading:Events()
    object UpcomingListError:Events()
    class UpcomingListSuccess(val upcomcingMovie:List<UpcomingMovie>):Events()
}