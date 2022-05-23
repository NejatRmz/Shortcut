package com.example.shortcut

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shortcut.data.remote.repository.comics.ComicsViewModel
import com.example.shortcut.databinding.ActivityMainBinding
import com.example.shortcut.network.ComicResource
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val comicsViewModel: ComicsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun observeComicInfo(){
        comicsViewModel.comicsInfo.observe(this) { data ->
            when (data.status) {
                ComicResource.Status.LOADING ->
                    Log.e(TAG, "loading")
                ComicResource.Status.SUCCESS -> {
                    data?.let {
                        Log.e(TAG, "Success")
                        data.data?.let { items ->
                            Log.e(TAG, items.toString())
                        }
                    }
                }
                ComicResource.Status.ERROR ->
                    data.message?.let {
                        Log.e(TAG, "Error")
                    }
            }
        }
    }



    private fun observeCurrentComicInfo(){
        comicsViewModel.currentComicsInfo.observe(this) { data ->
            when (data.status) {
                ComicResource.Status.LOADING ->
                    Log.e(TAG, "loading")
                ComicResource.Status.SUCCESS -> {
                    data?.let {
                        Log.e(TAG, "Success")
                        data.data?.let { items ->
                            Log.e(TAG, items.toString())
                        }
                    }
                }
                ComicResource.Status.ERROR ->
                    data.message?.let {
                        Log.e(TAG, "Error")
                    }
            }
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }

}