package com.example.shortcut.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shortcut.data.model.ComicItem


@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(vararg users: ComicItem)

    @Delete
    suspend fun deleteComic(comicItem: ComicItem)

    @Update
    fun updateComic(comicItem: ComicItem)

    @Query("SELECT * FROM comicitem")
    fun getAllComics(): LiveData<List<ComicItem>>

    @Query("SELECT * FROM comicitem")
    fun getAllComicsList(): List<ComicItem>

}