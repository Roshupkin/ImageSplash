package android.com.roshchupkin.unsplashapp.ui.fragments.Collection

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentCollectionBinding
import android.com.roshchupkin.unsplashapp.model.CollectionDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.CollectionAdapter
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

@AndroidEntryPoint
class CollectionFragment : Fragment(R.layout.fragment_collection), CollectionAdapter.Interaction {
    private val collectionViewModel: CollectionViewModel by viewModels()
    lateinit var collectionAdapter: CollectionAdapter

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCollectionBinding.bind(view)

        collectionViewModel.collection.observe(viewLifecycleOwner) {
            collectionAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.apply {
            recyclerView.apply {
                collectionAdapter = CollectionAdapter(this@CollectionFragment)
                adapter = collectionAdapter.withLoadStateHeaderAndFooter(
                    header = ImageLoadStateAdapter { collectionAdapter.retry() },
                    footer = ImageLoadStateAdapter { collectionAdapter.retry() }
                )
                buttonRetry.setOnClickListener { collectionAdapter.refresh() }
            }
        }


        collectionAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible =
                    loadState.source.refresh is LoadState.Error || loadState.source.prepend is LoadState.Loading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewSystemMessage.isVisible = loadState.source.refresh is LoadState.Error
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            }
        }
    }

    override fun onItemSelected(position: Int, item: CollectionDomain) {
        val bundle = bundleOf("itemIdCollection" to item.id)
        findNavController().navigate(R.id.action_collectionsFragment_to_imageFragment, bundle)
    }
}