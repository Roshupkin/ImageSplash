package android.com.roshchupkin.unsplashapp.ui.viewmodel

import android.com.roshchupkin.unsplashapp.repository.RandomImageRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class RandomImageViewModel
@ViewModelInject
constructor(
    private val randomImageRepository: RandomImageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    var dataState = randomImageRepository.getRandomPhoto().cachedIn(viewModelScope).asLiveData()



    fun clearAllRandomImage() {
        viewModelScope.launch { randomImageRepository.clearAllRandomImage() }
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "cats"
    }
}