package com.huda.submission_4_made.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huda.submission_4_made.entity.DataFilm

@Dao
interface FavoriteDao {
    @Insert
    fun insert(movie : DataFilm)

    @Query("DELETE FROM favorite")
    fun deleteAll()

    @Query("DELETE FROM favorite WHERE id= :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAll() : List<DataFilm>

    @Query("SELECT * FROM favorite WHERE id_film= :idFilm")
    fun getById(idFilm: Int) : List<DataFilm>

    @Query("SELECT * FROM favorite WHERE category= 'movie' ORDER BY id ASC")
    fun getMovie() : List<DataFilm>

    @Query("SELECT * FROM favorite WHERE category= 'tvshow' ORDER BY id ASC")
    fun getTvShow() : List<DataFilm>

}