package android.com.roshchupkin.unsplashapp.ui.viewmodel

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.model.Urls
import android.com.roshchupkin.unsplashapp.model.User
import android.com.roshchupkin.unsplashapp.repository.random.RandomImageRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class RandomImagesViewModel
@ViewModelInject
constructor(
    private val imageCacheMapper: ImageCacheMapper,
    private val randomImageRepository: RandomImageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var dataState = randomImageRepository.getRandomPhoto().cachedIn(viewModelScope).asLiveData()

}