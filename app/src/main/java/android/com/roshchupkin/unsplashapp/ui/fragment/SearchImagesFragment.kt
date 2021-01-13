package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentImageListBinding
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageAdapter
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageLoadStateAdapter
import android.com.roshchupkin.unsplashapp.ui.viewmodel.SearchImagesViewModel
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class SearchImagesFragment : Fragment(R.layout.fragment_image_list), ImageAdapter.Interaction {

    private val searchImagesViewModel: SearchImagesViewModel by viewModels()
    lateinit var imageAdapter: ImageAdapter

    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageListBinding.bind(view)

        val queryString: String = this.arguments?.getString("queryString").toString()

        binding.apply {
            recyclerView.apply {
                imageAdapter = ImageAdapter(this@SearchImagesFragment)
                adapter = imageAdapter.withLoadStateHeaderAndFooter(
                    header = ImageLoadStateAdapter { imageAdapter.retry() },
                    footer = ImageLoadStateAdapter { imageAdapter.retry() }
                )
                scrollToPosition(0)
            }
            searchImagesViewModel.getImageList(queryString).observe(viewLifecycleOwner) {
                imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                Log.e("ПРИШЛО","sssss   ${it.map {  }}")
            }
        }




    }

    override fun onItemSelected(position: Int, item: ImageDomain) {
        val bundle = bundleOf("itemID" to item.id)
        findNavController().navigate(R.id.action_searchImageFragment_to_detailImageFragment, bundle)
    }
}