package com.example.shortcut.data.remote

import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.data.model.TestCard
import retrofit2.Response
import retrofit2.http.*

interface IService {
    @GET("cards/{name}")
    suspend fun getTestCard(
        @Path("name") name: String
    ): Response<ArrayList<TestCard>>

    @GET("{number}/info.0.json")
    suspend fun getComics(
        @Path("number") number: Int
    ): Response<ComicItem>


    @GET("/info.0.json")
    suspend fun getCurrentComic(): Response<ComicItem>
}