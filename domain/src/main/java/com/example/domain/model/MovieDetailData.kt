package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies-detail")
data class MovieDetailData(
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