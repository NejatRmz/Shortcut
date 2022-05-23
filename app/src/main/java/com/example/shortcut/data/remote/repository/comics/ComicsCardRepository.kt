package com.example.shortcut.data.remote.repository.comics

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.network.ComicResource

interface ComicsCardRepository {
    /**
     * Provides test card list
     */
    fun getComic(name: Int): LiveData<ComicResource<ComicItem>>

    fun getCurrentComic(): LiveData<ComicResource<ComicItem>>
}