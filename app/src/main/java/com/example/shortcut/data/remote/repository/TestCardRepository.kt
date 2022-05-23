package com.example.shortcut.data.remote.repository

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.TestCard
import com.example.shortcut.network.ComicResource

interface TestCardRepository {
    /**
     * Provides test card list
     */
    fun getTestCard(name: String): LiveData<ComicResource<ArrayList<TestCard>>>
}