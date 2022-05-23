package com.example.shortcut.ui.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shortcut.R
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.databinding.ComicItemBinding


class HomeFragmentAdapter(private val list: ArrayList<ComicItem>, private  val viewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder>() {

    private val TAG = "HomeFragmentAdapter"

    private lateinit var binding: ComicItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentAdapter.ViewHolder {
        binding =
            ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentAdapter.ViewHolder, position: Int) {
        val comicItem: ComicItem = list[position]
        holder.bind(comicItem)
//        holder.itemView.setOnClickListener {
//            Log.i(TAG, comicItem.title)
//            addToFavorite(comicItem)
//        }
    }

    override fun getItemCount() = list.size

    fun updateDataList(newPostsList: List<ComicItem>) {
        list.clear()
        list.addAll(newPostsList)
        notifyDataSetChanged()
    }

    fun addData(listItems: ArrayList<ComicItem>) {
        var size = listItems.size
        listItems.addAll(listItems)
        var sizeNew = listItems.size
        notifyItemRangeChanged(size, sizeNew)
    }

    inner class ViewHolder(binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comicItem: ComicItem) {
            setLoadingImage(binding,comicItem.img)
            binding.title.text =comicItem.num.toString()
            binding.detail.text =comicItem.alt
            binding.favorite.setOnClickListener(View.OnClickListener {
                //Log.i(TAG, comicItem.title)
                addToFavorite(comicItem)
            })

            binding.imageView.setOnClickListener {
                Log.e("ITEMCLICKLISTENER", comicItem.toString())

                try {
                    val bundle = bundleOf("comic" to comicItem)
                    binding.root.findNavController().navigate(R.id.navigation_comic_detail, bundle)
                }catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }

        private fun setLoadingImage(comicItemBinding: ComicItemBinding, url : String) {
            Glide.with(comicItemBinding.root)
                .load(url)
                .into(comicItemBinding.imageView)
        }

        private fun addToFavorite(comicItem:ComicItem){
            Log.i(TAG, "Add item to favorites " + comicItem.title)
            viewModel.insertComic(comicItem)
        }
    }

}