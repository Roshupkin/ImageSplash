package android.com.roshchupkin.unsplashapp.ui.viewmodule

import android.com.roshchupkin.unsplashapp.repository.RandomImageRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class RandomImageViewModule
@ViewModelInject
constructor(
    private val randomImageRepository: RandomImageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

        val randomPhoto =   randomImageRepository.getRandomPhoto().cachedIn(viewModelScope)

}
