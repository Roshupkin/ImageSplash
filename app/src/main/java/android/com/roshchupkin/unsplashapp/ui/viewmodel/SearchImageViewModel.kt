package android.com.roshchupkin.unsplashapp.ui.viewmodel

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.repository.ImageByCollectionRepository
import android.com.roshchupkin.unsplashapp.repository.SearchImageRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

class SearchImageViewModel
@ViewModelInject
constructor(
    private val searchImageRepository: SearchImageRepository
) : ViewModel() {
    fun getImageList(queryString:String): LiveData<PagingData<ImageDomain>> =
        searchImageRepository.searchImage(queryString)
}