package android.com.roshchupkin.unsplashapp.utill

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exceptionMessage: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}