package android.com.roshchupkin.unsplashapp.ui.viewmodule


import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.repository.DetailImageRepository
import android.com.roshchupkin.unsplashapp.utill.DataState
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class DetailImageViewModule
@ViewModelInject
constructor(private val detailImageRepository: DetailImageRepository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<ImageDomain>> = MutableLiveData()

    val dataState: LiveData<DataState<ImageDomain>>
        get() = _dataState

    fun getImage(id: String) {
        viewModelScope.launch {
            detailImageRepository.getImage(id).onEach { _dataState.value = it }.launchIn(viewModelScope)
        }

    }


}