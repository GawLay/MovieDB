package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.domain.model.UpcomingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMovieDao {
    @Insert
    suspend fun insert(movieData: UpcomingMovie)

    @Query("SELECT * FROM `upcoming-movies`")
    fun getAllMovieList(): Flow<List<UpcomingMovie>>

    @Query("SELECT COUNT(*) FROM `upcoming-movies`")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovies(movies: List<UpcomingMovie>)
}