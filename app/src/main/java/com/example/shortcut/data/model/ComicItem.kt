package com.example.shortcut.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ComicItem(
    @PrimaryKey()
    @ColumnInfo(name = "num") var num: Int,
    @ColumnInfo(name = "month") var month: String,
    @ColumnInfo(name = "link") var link: String,
    @ColumnInfo(name = "year") var year: String,
    @ColumnInfo(name = "news") var news: String,
    @ColumnInfo(name = "safe_title") var safe_title: String,
    @ColumnInfo(name = "transcript") var transcript: String,
    @ColumnInfo(name = "alt") var alt: String,
    @ColumnInfo(name = "img") var img: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "day") var day: String,
) : Serializable