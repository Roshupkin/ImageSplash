package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.util.Log
import androidx.paging.*
import java.io.IOException


@ExperimentalPagingApi
class ImagePagingMediator(
    private val unsplashAPI: UnsplashAPI,
    private val randomImageDao: RandomImageDao,
    private val imageCacheMapper: ImageCacheMapper,
    private val imageNetworkMapper: ImageNetworkMapper
) :
    RemoteMediator<Int, RandomImageCacheEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RandomImageCacheEntity>
    ): MediatorResult {

        try {
            val listRandomImage = unsplashAPI.getRandomImage()
            val domainListRandomImage = imageNetworkMapper.mapFromEntityList(listRandomImage)
            val randomImageCache = imageCacheMapper.mapToEntityList(domainListRandomImage)
            randomImageDao.insertRandomImage(randomImageCache)

            val endOfPagnationReached = listRandomImage.isEmpty()
            Log.e("RA", "$endOfPagnationReached")
            return MediatorResult.Success(endOfPaginationReached = endOfPagnationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        }

    }
}


