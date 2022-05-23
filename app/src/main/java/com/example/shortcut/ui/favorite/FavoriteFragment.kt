package com.example.shortcut.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteFragmentAdapter: FavoriteFragmentAdapter

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
        favoriteFragmentAdapter = FavoriteFragmentAdapter(arrayListOf(), favoriteViewModel)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setupViewModel()
        observerLiveData()
    }

    private fun initRecyclerView() {
        binding.rvComics.adapter = favoriteFragmentAdapter
    }

    private fun setupViewModel() {
        favoriteViewModel.getAllComics()
    }


    private fun observerLiveData() {
        favoriteViewModel.getAllComics().observe(viewLifecycleOwner, Observer { listOfComics ->
            listOfComics?.let {
                allComics = it
                favoriteFragmentAdapter.updateDataList(allComics)
            }
        })
        if (allComics.isEmpty()) {
            binding.textFavorite.visibility = View.VISIBLE
            binding.rvComics.visibility = View.GONE
        } else {
            binding.textFavorite.visibility = View.GONE
            binding.rvComics.visibility = View.VISIBLE
        }
    }

}