package com.example.shortcut.ui.detail
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.comicDetailLiveData.observe(viewLifecycleOwner) { item ->
            item?.let {
                binding.tvAnnouncementDetailTitle.text = it.title
            }
        }
    }
}