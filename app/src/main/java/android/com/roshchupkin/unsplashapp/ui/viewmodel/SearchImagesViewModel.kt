package android.com.roshchupkin.unsplashapp.ui.viewmodel

import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.repository.search.SearchImageRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData

class SearchImagesViewModel
@ViewModelInject
constructor(
    private val searchImageRepository: SearchImageRepository
) : ViewModel() {
    fun getImageList(queryString:String): LiveData<PagingData<ImageDomain>> =
        searchImageRepository.searchImage(queryString)
}