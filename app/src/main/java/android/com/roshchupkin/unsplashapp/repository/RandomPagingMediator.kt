package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.util.Log
import androidx.paging.*
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class RandomPagingMediator(
    private val unsplashAPI: UnsplashAPI,
    private val randomImageDao: RandomImageDao,
    private val imageCacheMapper: ImageCacheMapper
) :
    RemoteMediator<Int, RandomImageCacheEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RandomImageCacheEntity>
    ): MediatorResult {

        try {
            val listRandomImage = unsplashAPI.getRandomImage()
            Log.e("RAndomPagingMediator", "$listRandomImage")


               val randomImageCache =  imageCacheMapper.mapToEntityList(listRandomImage)
                randomImageDao.insertRandomImage(randomImageCache)




            val endOfPagnationReached = listRandomImage.isEmpty()
            Log.e("RA", "$endOfPagnationReached")
            return MediatorResult.Success(endOfPaginationReached =  endOfPagnationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        }


    }
}


