package com.example.shortcut.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shortcut.data.model.ComicItem

@Database(entities = [ComicItem::class], version = 1)
abstract class ComicDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}