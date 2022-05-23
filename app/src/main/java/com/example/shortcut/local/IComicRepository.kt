package com.example.shortcut.local

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem

interface IComicRepository {
    suspend fun insertComic(comicItem: ComicItem)

    suspend fun deleteComic(comicItem: ComicItem)

    suspend fun updateComic(comicItem: ComicItem)

    fun getAllComics(): LiveData<List<ComicItem>>
}