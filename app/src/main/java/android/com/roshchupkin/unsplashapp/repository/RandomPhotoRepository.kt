package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.com.roshchupkin.unsplashapp.utill.DataState
import kotlinx.coroutines.flow.flow

class RandomPhotoRepository(
    private val unsplashAPI: UnsplashAPI
) {
    suspend fun getRundomFilm()= flow<DataState<List<RandomPhotoListResponse>>> {
        emit(DataState.Loading)
        val randomPhoto = unsplashAPI.getRandomPhoto()
        emit(DataState.Success(randomPhoto))

    }
}