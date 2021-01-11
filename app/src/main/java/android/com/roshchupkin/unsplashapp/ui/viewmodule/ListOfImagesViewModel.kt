package android.com.roshchupkin.unsplashapp.ui.viewmodule

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.repository.ImageByCollectionRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn


class ListOfImagesViewModel
@ViewModelInject
constructor(
    private val imageByCollectionRepository: ImageByCollectionRepository
) : ViewModel() {
    fun getImageList(id: Int): LiveData<PagingData<ImageDomain>> =
        imageByCollectionRepository.getListOfImagesById(id).cachedIn(viewModelScope)
}