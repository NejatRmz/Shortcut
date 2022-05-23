package com.example.shortcut.di

import android.content.Context
import androidx.room.Room
import com.example.shortcut.data.MainRepository
import com.example.shortcut.local.ComicRepository
import com.example.shortcut.local.IComicRepository
import com.example.shortcut.roomdb.ComicDao
import com.example.shortcut.roomdb.ComicDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainRepository() = MainRepository()

    @Singleton
    @Provides
    fun injectNormalRepo(dao: ComicDao) = ComicRepository(dao) as IComicRepository

    @Singleton
    @Provides
    fun injectComicRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ComicDatabase::class.java, "ComicDB")
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun injectComicDao(database: ComicDatabase) = database.comicDao()
}