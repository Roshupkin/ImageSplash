package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomPhotoStateEvent
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomPhotoViewModule
import android.com.roshchupkin.unsplashapp.utill.DataState
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_photo.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RandomPhotoFragment:Fragment(R.layout.fragment_random_photo) {
    private val randomPhotoViewModule:RandomPhotoViewModule by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomPhotoViewModule.setRandomPhoto(RandomPhotoStateEvent.GetPhotoEvent)
        subscribeObserver()
    }
    fun subscribeObserver(){
        randomPhotoViewModule.dataState.observe(viewLifecycleOwner,{datastate ->
         when(datastate){
            is DataState.Success<List<RandomPhotoListResponse>> ->{
               textView1.text = datastate.data.get(1).urls.raw

            }
         }

        })
    }
}