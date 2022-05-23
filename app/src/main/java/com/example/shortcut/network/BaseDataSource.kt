package com.example.shortcut.network

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource {
    val TAG = "BaseDataSource"
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ComicResource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return ComicResource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ComicResource<T> {
        Log.e(TAG,message)
        return ComicResource.error("Network call has failed for a following reason: $message")
    }

}