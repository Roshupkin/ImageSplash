package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomPhotoViewModule
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_photo.*


import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomPhotoFragment : Fragment(R.layout.fragment_random_photo), PhotoAdapter.Interaction {
    private val randomPhotoViewModule: RandomPhotoViewModule by viewModels()
    lateinit var photoAdapter: PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        randomPhotoViewModule.randomPhoto.observe(viewLifecycleOwner) {
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            photoAdapter = PhotoAdapter(this@RandomPhotoFragment)
            adapter = photoAdapter
        }

    }


    override fun onItemSelected(position: Int, item: RandomPhotoListResponse) {
        TODO("Not yet implemented")
    }


}
