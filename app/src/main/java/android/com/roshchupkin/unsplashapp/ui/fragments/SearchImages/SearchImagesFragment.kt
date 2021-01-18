package android.com.roshchupkin.unsplashapp.ui.fragments.SearchImages

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentImageListBinding
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageAdapter
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageLoadStateAdapter
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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
                buttonRetry.setOnClickListener { imageAdapter.retry() }
            }
            searchImagesViewModel.getImageList(queryString).observe(viewLifecycleOwner) {
                imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }


        imageAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible =
                    loadState.source.refresh is LoadState.Error || loadState.source.prepend is LoadState.Loading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewSystemMessage.isVisible = loadState.source.refresh is LoadState.Error
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading


                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    imageAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewSystemMessage.isVisible = true
                    textViewSystemMessage.text = "Ваш запрос не найден"
                } else {
                    textViewSystemMessage.isVisible = false
                }

            }
        }

    }

    override fun onItemSelected(position: Int, item: ImageDomain) {
        val bundle = bundleOf("itemID" to item.id)
        findNavController().navigate(R.id.action_searchImageFragment_to_detailImageFragment, bundle)
    }
}