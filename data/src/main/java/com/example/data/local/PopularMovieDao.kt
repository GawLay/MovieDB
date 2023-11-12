package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao {
    @Insert
    suspend fun insert(movieData: PopularMovie)

    @Query("SELECT * FROM `popular-movies`")
    fun getAllMovieList(): Flow<List<PopularMovie>>

    @Query("SELECT COUNT(*) FROM `popular-movies`")
    suspend fun getRowCount(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovies(movies: List<PopularMovie>)

}