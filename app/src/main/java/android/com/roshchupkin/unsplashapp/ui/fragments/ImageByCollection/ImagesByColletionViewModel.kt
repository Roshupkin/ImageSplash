package android.com.roshchupkin.unsplashapp.ui.fragments.ImageByCollection

import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.repository.collection.image.ImageByCollectionRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn


class ImagesByColletionViewModel
@ViewModelInject
constructor(
    private val imageByCollectionRepository: ImageByCollectionRepository
) : ViewModel() {
    fun getImageList(id: Int): LiveData<PagingData<ImageDomain>> =
        imageByCollectionRepository.getListOfImagesById(id).cachedIn(viewModelScope)
}