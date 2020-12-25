package android.com.roshchupkin.unsplashapp.ui.viewmodule

import android.com.roshchupkin.unsplashapp.repository.RandomPhotoRepository
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class RandomPhotoViewModule
@ViewModelInject
constructor(
    private val randomPhotoRepository: RandomPhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

        val randomPhoto =   randomPhotoRepository.getRandomPhoto().cachedIn(viewModelScope)

}


/*
sealed class RandomPhotoStateEvent {
    object GetPhotoEvent : RandomPhotoStateEvent()

}*/
