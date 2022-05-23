package com.example.shortcut.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * MainApplication
 *
 * @author b00557735
 * @since 2022-02-14
 */
@HiltAndroidApp
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}