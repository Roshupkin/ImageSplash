package android.com.roshchupkin.unsplashapp.ui.viewmodule

import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.com.roshchupkin.unsplashapp.repository.RandomPhotoRepository
import android.com.roshchupkin.unsplashapp.utill.DataState
import android.com.roshchupkin.unsplashapp.ui.viewmodule.RandomPhotoStateEvent.*
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RandomPhotoViewModule
@ViewModelInject
constructor(
    private val randomPhotoRepository: RandomPhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<RandomPhotoListResponse>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<RandomPhotoListResponse>>>
        get() = _dataState

    fun setRandomPhoto(randomPhotoStateEvent: RandomPhotoStateEvent) {
        viewModelScope.launch {
            when (randomPhotoStateEvent) {
                is GetPhotoEvent -> {
                    randomPhotoRepository.getRundomFilm().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class RandomPhotoStateEvent {
    object GetPhotoEvent : RandomPhotoStateEvent()

}