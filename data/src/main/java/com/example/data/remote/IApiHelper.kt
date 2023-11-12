package com.example.data.remote

import com.example.domain.model.MovieDetailData
import com.example.domain.model.PopularMovieListResponse
import com.example.domain.model.UpcomingMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiHelper {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "21da79b66e24bcf7e8e0c297344dd1b1",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PopularMovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "21da79b66e24bcf7e8e0c297344dd1b1",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): UpcomingMovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "21da79b66e24bcf7e8e0c297344dd1b1",
        @Query("language") language: String = "en-US"
    ): MovieDetailData

}