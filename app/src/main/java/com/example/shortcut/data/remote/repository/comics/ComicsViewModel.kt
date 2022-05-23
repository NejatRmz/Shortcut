package com.example.shortcut.data.remote.repository.comics

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.network.ComicResource
import com.example.shortcut.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val comicsCardRepository : ComicsCardRepositoryImpl
) : BaseViewModel() {

    lateinit var _comicInfo : LiveData<ComicResource<ComicItem>>
    lateinit var _currentComicInfo : LiveData<ComicResource<ComicItem>>

    val comicsInfo: LiveData<ComicResource<ComicItem>>
        get() = _comicInfo

    val currentComicsInfo: LiveData<ComicResource<ComicItem>>
        get() = _currentComicInfo

    init {
        getComic(614)
        getCurrentComic()
    }

    private fun getComic(number: Int){
        _comicInfo = comicsCardRepository.getComic(number)
    }

    private fun getCurrentComic(){
        _currentComicInfo = comicsCardRepository.getCurrentComic()
    }
}
