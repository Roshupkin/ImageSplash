package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentCollectionBinding
import android.com.roshchupkin.unsplashapp.model.collection.CollectionDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.CollectionAdapter
import android.com.roshchupkin.unsplashapp.ui.viewmodel.CollectionViewModel
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment: Fragment(R.layout.fragment_collection), CollectionAdapter.Interaction {
    private val collectionViewModel:CollectionViewModel by viewModels()
    lateinit var collectionAdapter: CollectionAdapter

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCollectionBinding.bind(view)

        collectionViewModel.collection.observe(viewLifecycleOwner){
            collectionAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.apply {
            recyclerView.apply {
                collectionAdapter = CollectionAdapter(this@CollectionFragment)
            adapter = collectionAdapter
        } }
    }

    override fun onItemSelected(position: Int, item: CollectionDomain) {
        val bundle = bundleOf("itemIdCollection" to item.id)
        findNavController().navigate(R.id.action_collectionsFragment_to_imageFragment, bundle)
    }
}