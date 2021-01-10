package android.com.roshchupkin.unsplashapp.utill

import com.bumptech.glide.load.HttpException

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exceptionMessage: String) : DataState<Nothing>()
    data class HttpError(val httpException: HttpException) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}