package com.example.shortcut.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.shortcut.BuildConfig
import kotlinx.coroutines.Dispatchers

private const val TAG = "CMNetwork"

fun <T> performGetOperation(networkCall: suspend () -> ComicResource<T>): LiveData<ComicResource<T>> = liveData(Dispatchers.IO) {
        emit(ComicResource.loading())

        val responseStatus = networkCall.invoke()
        log("Network request begins")
        try {
            if (responseStatus.status == ComicResource.Status.SUCCESS ) {
                if(responseStatus.data != null){
                    emit(ComicResource.success(responseStatus.data))
                }else{
                    emit(ComicResource.success())
                }
                log("Network request success")
            }else if (responseStatus.status == ComicResource.Status.ERROR) {
                emit(ComicResource.error(responseStatus.message!!))
                logError("Network request error")
            }
        }catch (e: Exception) {
            logError("Network request error")
            if (e.message != null)
                emit(ComicResource.error(e.message!!))
        }


    }

private fun log(logMessage: String) {
    onDebug {
        Log.i(TAG, logMessage)
    }
}

private fun logError(logMessage: String) {
    onDebug {
        Log.e(TAG, logMessage)
    }
}

private fun onDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block.invoke()
    }
}