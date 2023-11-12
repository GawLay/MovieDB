package com.example.main.ui.list

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.repository.State
import com.example.main.databinding.ActivityMovieListBinding
import com.example.main.ui.base.BaseActivity
import com.example.main.ui.detail.MovieDetailActivity
import com.example.main.ui.list.adapter.PopularMovieListAdapter
import com.example.main.ui.list.adapter.UpcomingMovieListAdapter
import com.example.utility.constant.BundleKeys
import com.example.utility.constant.goneExt
import com.example.utility.constant.showLog
import com.example.utility.constant.showToast
import com.example.utility.constant.startIntentWithData
import com.example.utility.constant.visibleExt
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListActivity : BaseActivity<ActivityMovieListBinding>() {
    private val viewModel: MovieListViewModel by viewModel()
    private lateinit var popularAdapter: PopularMovieListAdapter
    private lateinit var upcomingAdapter: UpcomingMovieListAdapter
    override fun setBinding(inflater: LayoutInflater): ActivityMovieListBinding {
        return ActivityMovieListBinding.inflate(inflater)
    }

    override fun initViews() {
        initRcViews()
        lifecycleScope.launch {
            fetchedPopularList()
            fetchedUpcomingList()
        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.moviesListEvent.collectLatest {
                when(it){
                    is Events.UpcomingListError->{
                        binding.includeRetry.frameRetry.visibleExt()
                    }
                    is Events.UpcomingListLoading->{
                        binding.includeLoading.frameLoading.visibleExt()
                    }
                    is Events.UpcomingListSuccess->{
                        binding.includeLoading.frameLoading.goneExt()
                        binding.includeRetry.frameRetry.goneExt()
                        upcomingAdapter.submitList(it.upcomcingMovie)
                    }
                    is Events.PopularListError->{
                        binding.includeRetry.frameRetry.visibleExt()
                    }
                    is Events.PopularListLoading->{
                        binding.includeLoading.frameLoading.visibleExt()
                    }
                    is Events.PopularListSuccess->{
                        binding.includeLoading.frameLoading.goneExt()
                        binding.includeRetry.frameRetry.goneExt()
                        popularAdapter.submitList(it.popularMovie)
                    }
                }

            }


        }

    }

    private  fun fetchedUpcomingList() {
        viewModel.getUpcomingList()
    }

    private  fun fetchedPopularList() {
        viewModel.getPopularList()
    }

    private fun initRcViews() {
        popularAdapter = PopularMovieListAdapter(::onItemClick)
        upcomingAdapter = UpcomingMovieListAdapter(::onItemClick)
        binding.rcPopular.apply {
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
        binding.rcUpcoming.apply {
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }
    }

    private fun onItemClick(id: Int) {
        startIntentWithData<MovieDetailActivity> {
            putExtra(BundleKeys.MOVIE_ID_KEY.key, id)
        }
    }
}