package com.example.shortcut.network

data class ComicResource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T? = null): ComicResource<T> {
            return ComicResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ComicResource<T> {
            return ComicResource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ComicResource<T> {
            return ComicResource(Status.LOADING, data, null)
        }
    }
}