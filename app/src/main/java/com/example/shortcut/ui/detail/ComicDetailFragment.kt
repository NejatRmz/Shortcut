package com.example.shortcut.ui.detail
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.shortcut.R
import com.example.shortcut.databinding.FragmentComicDetailBinding
import com.example.shortcut.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailFragment : Fragment() {

    private lateinit var viewModel: ComicDetailViewModel
    private var binding: FragmentComicDetailBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ComicDetailViewModel::class.java]
        binding = FragmentComicDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            viewModel.getDataFromArgs(ComicDetailFragmentArgs.fromBundle(it).comic)
        }


        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(binding.root).navigate(R.id.navigation_home)                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.comicDetailLiveData.observe(viewLifecycleOwner) { item ->
            item?.let {
                setLoadingImage(binding,it.img)
                binding.title.text = it.title
                binding.detail.text = it.alt
                binding.month.text = it.month
                binding.year.text = it.year
            }
        }
    }

    private fun setLoadingImage(binding: FragmentComicDetailBinding, url : String) {
        Glide.with(binding.root)
            .load(url)
            .into(binding.imageView)
    }
}