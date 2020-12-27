package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomImageViewModule
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_photo.*


import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomImageFragment : Fragment(R.layout.fragment_random_photo), PhotoAdapter.Interaction {
    private val randomImageViewModule: RandomImageViewModule by viewModels()
    lateinit var photoAdapter: PhotoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        randomImageViewModule.randomPhoto.observe(viewLifecycleOwner) {
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        recycler_view.apply {
            photoAdapter = PhotoAdapter(this@RandomImageFragment)
            adapter = photoAdapter
        }

    }


    override fun onItemSelected(position: Int, item: RandomImageNetworkEntity) {
        Toast.makeText(activity, "$position $item", Toast.LENGTH_SHORT).show()
    }


}
