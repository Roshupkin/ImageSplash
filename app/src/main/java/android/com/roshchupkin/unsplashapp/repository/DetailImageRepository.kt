package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.com.roshchupkin.unsplashapp.utill.DataState
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DetailImageRepository(
    private val unsplashAPI: UnsplashAPI,
    private val imageNetworkMapper: ImageNetworkMapper,

    ) {
    suspend fun getImage(id: String): Flow<DataState<ImageDomain>> = flow {

        emit(DataState.Loading)
        try {
            val imageNetwork = unsplashAPI.getImageById(id)
           val image = imageNetworkMapper.mapFromEntity(imageNetwork)
            emit(DataState.Success(image))
        } catch (e: HttpException) {
            emit(DataState.HttpError(e))
        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(DataState.Error("Network Failure"))
                else -> emit(DataState.Error("Conversion Error"))
            }
        }
    }


}