package com.example.shortcut.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.data.remote.repository.comics.ComicsCardRepositoryImpl
import com.example.shortcut.network.ComicResource
import com.example.shortcut.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    private val comicsCardRepository : ComicsCardRepositoryImpl
) : BaseViewModel() {

    val comicDetailLiveData = MutableLiveData<ComicItem>()

    fun getDataFromArgs(comicItem: ComicItem) {
        comicDetailLiveData.value = comicItem
    }

    fun getComicById(announcementId: String) : LiveData<ComicResource<ComicItem>>{
      return comicsCardRepository.getCurrentComic()
    }
}