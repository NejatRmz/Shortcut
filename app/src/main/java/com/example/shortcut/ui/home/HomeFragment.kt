package com.example.shortcut.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.databinding.FragmentHomeBinding
import com.example.shortcut.network.ComicResource
import com.example.shortcut.utils.PaginationScrollListener
import com.example.shortcut.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding by autoCleared()
    private lateinit var homeFragmentAdapter: HomeFragmentAdapter

    private lateinit var comicItemList : ArrayList<ComicItem>

    var isLoadingFlag = false
    var isLastPageFlag = false
    private var pageNum = 10
    private var count = 1



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeFragmentAdapter = HomeFragmentAdapter(arrayListOf())
        comicItemList = arrayListOf()

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvComics.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComics.adapter = homeFragmentAdapter

        getCurrentComic()
        searchById()
//        for (i in 0 until pageNum){
//            getComicById(i)
//        }

        //recyclerViewScrollListener()

    }


    private fun searchById(){
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                getComicById(s.toString().toInt())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }


    /**
     * This method listen scroll and load more data when scroll reach end.
     */
    private fun recyclerViewScrollListener(){
        binding.rvComics!!.addOnScrollListener(object :
            PaginationScrollListener(binding.rvComics.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoadingFlag = true
                pageNum+=10
                getMoreItems(pageNum)
            }

            override val isLastPage: Boolean
                get() = isLastPageFlag
            override val isLoading: Boolean
                get() = isLoadingFlag
        })
    }


    fun getMoreItems(pageNum : Int) {

        Toast.makeText(context, "Load More Items", Toast.LENGTH_SHORT).show()
        isLoadingFlag = false
        for(i in pageNum until pageNum*10)
        {
            getComicById(i)
        }
        homeFragmentAdapter.addData(comicItemList)
    }
    private fun getCurrentComic(){
        homeViewModel.getCurrentComic().observe(viewLifecycleOwner) { data ->
            when (data.status) {
                ComicResource.Status.LOADING ->
                    Log.e(TAG, "loading")
                ComicResource.Status.SUCCESS -> {
                    data?.let {
                        Log.e(TAG, "Success")
                        data.data?.let { items ->
                            //Log.e(TAG, items.toString())
                            comicItemList.add(items)
                            homeFragmentAdapter.updateDataList(comicItemList)
                            getComicById(1)
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

    private fun getComicById(id:Int){
        isLoadingFlag = false
        Log.e(TAG, "item: $id")
        homeViewModel.getComicById(id).observe(viewLifecycleOwner) { data ->
            when (data.status) {
                ComicResource.Status.LOADING ->
                    Log.e(TAG, "loading")
                ComicResource.Status.SUCCESS -> {
                    data?.let {
                        //Log.e(TAG, "Success")
                        data.data?.let { items ->
                            //Log.e(TAG, items.toString())
                            comicItemList.add(items)
                            homeFragmentAdapter.updateDataList(comicItemList)
                        }
                    }
                }
                ComicResource.Status.ERROR ->
                    data.message?.let {
                        Log.e(TAG, "Error")
                    }
            }
        }

        count++
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}

