package com.example.shortcut.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.databinding.FragmentFavoriteBinding
import com.example.shortcut.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var listAdapter: FavoriteFragmentAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    lateinit var allComics: List<ComicItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allComics = mutableListOf()
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.rvComics.layoutManager = LinearLayoutManager(requireContext())
        listAdapter = FavoriteFragmentAdapter(arrayListOf(), favoriteViewModel)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setupViewModel()
        observerLiveData()
    }

    private fun initRecyclerView() {
        binding.rvComics.adapter = listAdapter
    }

    private fun setupViewModel() {
        favoriteViewModel.getAllComics()
    }


    private fun observerLiveData() {
        favoriteViewModel.getAllComics().observe(viewLifecycleOwner, Observer { listOfComics ->
            listOfComics?.let {
                allComics = it
                listAdapter.updateDataList(allComics)
            }
        })
    }

}