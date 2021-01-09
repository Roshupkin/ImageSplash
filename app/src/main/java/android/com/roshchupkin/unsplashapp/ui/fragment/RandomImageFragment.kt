package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.databinding.FragmentRandomPhotoBinding
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomImageViewModule
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint


import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@ExperimentalPagingApi
class RandomImageFragment
constructor() : Fragment(R.layout.fragment_random_photo), PhotoAdapter.Interaction {
    private val randomImageViewModule: RandomImageViewModule by viewModels()
    lateinit var photoAdapter: PhotoAdapter

    private var _binding: FragmentRandomPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRandomPhotoBinding.bind(view)

        randomImageViewModule.dataState.observe(viewLifecycleOwner) {
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        binding.apply {
            recyclerView.apply {
                photoAdapter = PhotoAdapter(this@RandomImageFragment)
                adapter = photoAdapter
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
        Toast.makeText(activity, "$position $item", Toast.LENGTH_SHORT).show()
    }


}
