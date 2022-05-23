package com.example.shortcut.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.local.IComicRepository
import com.example.shortcut.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: IComicRepository
) : ViewModel() {

    private val _comicList: MutableLiveData<List<ComicItem>> = MutableLiveData()

    val comicList: MutableLiveData<List<ComicItem>>
        get() = _comicList

    init {
        getAllComics()
    }

    fun delete(comicItem: ComicItem) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteComic(comicItem)
        }
    }

    fun getAllComics(): LiveData<List<ComicItem>> {
        Log.e("DEBUG", "View model getallcomics")
        return repository.getAllComics()
    }
}