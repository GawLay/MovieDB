package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.domain.model.MovieDetailData
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {


    @Query("SELECT * FROM `movies-detail` WHERE id== :movieInt")
    suspend fun getMovieDetail(movieInt: Int): MovieDetailData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieDetailData: MovieDetailData)

}