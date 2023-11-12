package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PopularMovieListResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_result")
    val totalResults: Int?,
    @SerializedName("results")
    val results: List<PopularMovie> = listOf()
)

@Entity(tableName = "popular-movies")
data class PopularMovie(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("popularity")
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overView: String?,
    @SerializedName("release_date")
    val releaseDate: String?

)