package com.example.shortcut.data.model

import java.io.Serializable

data class ComicItem(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safe_title: String,
    var transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String,
) : Serializable