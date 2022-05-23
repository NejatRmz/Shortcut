package com.example.shortcut.local


import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.roomdb.ComicDao
import javax.inject.Inject

class ComicRepository @Inject constructor(private val comicDao: ComicDao) : IComicRepository {

    override suspend fun insertComic(comicItem: ComicItem) {
        comicDao.insertComic(comicItem)
    }

    override suspend fun deleteComic(comicItem: ComicItem) {
        comicDao.deleteComic(comicItem)
    }

    override suspend fun updateComic(comicItem: ComicItem) {
        comicDao.updateComic(comicItem)
    }

    override fun getAllComics(): LiveData<List<ComicItem>> {
        return comicDao.getAllComics()
    }

}