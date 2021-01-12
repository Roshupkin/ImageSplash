package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.databinding.FragmentRandomPhotoBinding
import android.com.roshchupkin.unsplashapp.ui.adapters.RandomImageAdapter
import android.com.roshchupkin.unsplashapp.ui.viewmodel.RandomImageViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@ExperimentalPagingApi
class RandomImageFragment
@Inject
constructor() : Fragment(R.layout.fragment_random_photo), RandomImageAdapter.Interaction {
    private val randomImageViewModel: RandomImageViewModel by viewModels()
    lateinit var randomImageAdapter: RandomImageAdapter

    private var _binding: FragmentRandomPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        randomImageViewModel.clearAllRandomImage()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRandomPhotoBinding.bind(view)

        randomImageViewModel.dataState.observe(viewLifecycleOwner) {
            randomImageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.apply {
            recyclerView.apply {
                randomImageAdapter = RandomImageAdapter(this@RandomImageFragment)
                adapter = randomImageAdapter
            }
        }


      /*  photoAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

               *//* // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    photoAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }*//*
            }
        }*/


    }


    override fun onItemSelected(position: Int, item: RandomImageCacheEntity) {
       val bundle = bundleOf("itemID" to item.id)
        findNavController().navigate(R.id.action_randomPhotoFragment_to_detailImageFragment, bundle)
    }


}
