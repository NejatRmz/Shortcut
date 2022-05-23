package com.example.shortcut.data.remote.repository

import androidx.lifecycle.LiveData
import com.example.shortcut.data.model.TestCard
import com.example.shortcut.data.remote.RemoteDataSource
import com.example.shortcut.network.ComicResource
import com.example.shortcut.network.performGetOperation
import javax.inject.Inject

/**
 * 功能描述
 *
 * @author b00557735
 * @since 2022-03-10
 */
class TestCardRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :TestCardRepository{

    override fun getTestCard(name: String):LiveData<ComicResource<ArrayList<TestCard>>> {
        return performGetOperation( networkCall = { remoteDataSource.getTestCard(name = name) })
    }

}