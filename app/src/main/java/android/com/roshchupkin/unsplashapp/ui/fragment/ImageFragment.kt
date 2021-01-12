package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentImageBinding
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageAdapter
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageLoadStateAdapter
import android.com.roshchupkin.unsplashapp.ui.viewmodel.ListOfImagesViewModel
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageFragment
@Inject
constructor() : Fragment(R.layout.fragment_image), ImageAdapter.Interaction {
    private val imageListViewModel: ListOfImagesViewModel by viewModels()
    lateinit var imageAdapter: ImageAdapter

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageBinding.bind(view)

        val idCollection: Int = this.arguments?.getInt("itemIdCollection") ?: 22

        imageListViewModel.getImageList(idCollection).observe(viewLifecycleOwner) {
            imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.apply {
            recyclerView.apply {
                imageAdapter = ImageAdapter(this@ImageFragment)
                adapter = imageAdapter.withLoadStateHeaderAndFooter(
                    header = ImageLoadStateAdapter { imageAdapter.retry() },
                    footer = ImageLoadStateAdapter { imageAdapter.retry() }
                )
            }
        }
    }

    override fun onItemSelected(position: Int, item: ImageDomain) {
        val bundle = bundleOf("itemID" to item.id)
        findNavController().navigate(R.id.action_imageFragment_to_detailImageFragment, bundle)
    }
}