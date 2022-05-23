package com.example.shortcut.ui.favorite

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.shortcut.R
import com.example.shortcut.data.model.ComicItem
import com.example.shortcut.databinding.ComicItemBinding
import com.example.shortcut.ui.home.HomeViewModel


class FavoriteFragmentAdapter(
    private val list: ArrayList<ComicItem>,
    private val viewModel: FavoriteViewModel
) :
    RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolder>() {

    private val TAG = "HomeFragmentAdapter"

    private lateinit var binding: ComicItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteFragmentAdapter.ViewHolder {
        binding =
            ComicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteFragmentAdapter.ViewHolder, position: Int) {
        val comicItem: ComicItem = list[position]
        holder.bind(comicItem)
    }

    override fun getItemCount() = list.size

    fun updateDataList(newPostsList: List<ComicItem>) {
        list.clear()
        list.addAll(newPostsList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comicItem: ComicItem) {
            setLoadingImage(binding, comicItem.img)
            binding.title.text = comicItem.num.toString()
            binding.detail.text = comicItem.alt
            binding.favorite.setOnClickListener(View.OnClickListener {
                //Log.i(TAG, comicItem.title)
                removeFromFavorite(comicItem)
            })

            binding.imageView.setOnClickListener {
                Log.e("ITEMCLICKLISTENER", comicItem.toString())

                try {
                    val bundle = bundleOf("comic" to comicItem)
                    binding.root.findNavController().navigate(R.id.navigation_comic_detail, bundle)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }

        private fun setLoadingImage(comicItemBinding: ComicItemBinding, url: String) {
            Glide.with(comicItemBinding.root)
                .load(url)
                .into(comicItemBinding.imageView)
        }

        private fun removeFromFavorite(comicItem: ComicItem) {
            Log.i(TAG, "Add item to favorites " + comicItem.title)
            viewModel.delete(comicItem)
            notifyDataSetChanged()
        }
    }

}