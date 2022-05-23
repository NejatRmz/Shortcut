package com.example.shortcut.data.remote.repository.comics

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.data.remote.RemoteDataSource
import com.example.shortcut.network.ComicResource
import com.example.shortcut.network.performGetOperation
import javax.inject.Inject

class ComicsCardRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : ComicsCardRepository{

    override fun getComic(name: Int):LiveData<ComicResource<ComicItem>> {
        return performGetOperation( networkCall = { remoteDataSource.getComics(number = name) })
    }

    override fun getCurrentComic():LiveData<ComicResource<ComicItem>> {
        return performGetOperation( networkCall = { remoteDataSource.getCurrentComic() })
    }
}