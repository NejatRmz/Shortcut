package com.example.shortcut.data.remote

import com.example.shortcut.network.BaseDataSource
import javax.inject.Inject

/**
 * RemoteDataSource : connect repositories and endpoints
 *
 * @author b00557735
 * @since 2022-02-28
 */
class RemoteDataSource @Inject constructor(
    private val service: IService
) : BaseDataSource() {

    suspend fun getTestCard(name: String) = getResult { service.getTestCard(name = name) }

    suspend fun getComics(number: Int) = getResult { service.getComics(number = number) }
    suspend fun getCurrentComic() = getResult { service.getCurrentComic() }

}