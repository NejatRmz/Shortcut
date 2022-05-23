package com.example.shortcut.ui.home

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.data.remote.repository.comics.ComicsCardRepositoryImpl
import com.example.shortcut.network.ComicResource
import com.example.shortcut.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val comicsCardRepository : ComicsCardRepositoryImpl
) : BaseViewModel() {

    lateinit var _comicInfo : LiveData<ComicResource<ComicItem>>
    lateinit var _currentComicInfo : LiveData<ComicResource<ComicItem>>

    val comicsInfo: LiveData<ComicResource<ComicItem>>
        get() = _comicInfo

    val currentComicsInfo: LiveData<ComicResource<ComicItem>>
        get() = _currentComicInfo

    init {
        getCurrentComic()
        getComicById(1)
    }


    fun getCurrentComic(): LiveData<ComicResource<ComicItem>>{
        _currentComicInfo = comicsCardRepository.getCurrentComic()
        return _currentComicInfo
    }

    fun getComicById(number: Int) : LiveData<ComicResource<ComicItem>>{
        _comicInfo = comicsCardRepository.getComic(number)
        return _comicInfo
    }
}
