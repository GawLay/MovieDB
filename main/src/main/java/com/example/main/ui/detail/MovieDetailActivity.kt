package com.example.main.ui.detail

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.domain.model.MovieDetailData
import com.example.domain.repository.State
import com.example.main.databinding.ActivityMovieDetailBinding
import com.example.main.ui.base.BaseActivity
import com.example.utility.constant.BundleKeys
import com.example.utility.constant.UrlConfig
import com.example.utility.constant.goneExt
import com.example.utility.constant.showToast
import com.example.utility.constant.visibleExt
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {
    private val viewModel: MovieDetailViewModel by viewModel()
    private val movieId: Int by lazy {
        intent.getIntExtra(BundleKeys.MOVIE_ID_KEY.key, 0)
    }

    override fun setBinding(inflater: LayoutInflater): ActivityMovieDetailBinding {
        return ActivityMovieDetailBinding.inflate(inflater)
    }

    override fun initViews() {
        getDetails()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.movieDetailEvents.collectLatest {
                when (it) {
                    is Events.MovieDetailLoading -> {
                        //show db value
                        //check 1st time
                        binding.includeRetry.frameRetry.goneExt()
                        binding.includeLoading.frameLoading.visibleExt()
                    }

                    is Events.MovieDetailSuccess -> {
                        binding.includeLoading.frameLoading.goneExt()
                        binding.includeRetry.frameRetry.goneExt()
                        setupViews(it.movieDetailData)
                    }

                    is Events.MovieDetailError -> {
                        //show db value if exist
                        binding.includeLoading.frameLoading.goneExt()
                        binding.includeRetry.frameRetry.visibleExt()
                        showToast("Failed")
                    }
                }
            }
        }
    }

    override fun onClickEvents() {
        binding.includeRetry.frameRetry.setOnClickListener {
            getDetails()
        }
    }

    private fun getDetails() {
        viewModel.getMovieDetail(movieId)

    }

    private fun setupViews(data: MovieDetailData?) {
        if (data != null) {
            binding.apply {
                tvMovieName.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvDescription.text = data.overView
                Glide.with(this@MovieDetailActivity)
                    .load("${UrlConfig.IMAGE_URL.value}${data.posterPath}").into(ivPoster)
            }
        } else {
            showToast("Something wrong! Retry pls")
            binding.includeRetry.frameRetry.visibleExt()
        }
    }
}