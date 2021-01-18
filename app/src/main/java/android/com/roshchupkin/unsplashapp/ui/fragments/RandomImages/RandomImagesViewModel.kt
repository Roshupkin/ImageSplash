package android.com.roshchupkin.unsplashapp.ui.fragments.RandomImages

import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.repository.random.RandomImageRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class RandomImagesViewModel
@ViewModelInject
constructor(
    randomImageRepository: RandomImageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var dataState = randomImageRepository.getRandomPhoto().cachedIn(viewModelScope).asLiveData()

}