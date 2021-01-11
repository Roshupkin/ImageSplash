package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RandomImageRepository(
    private val unsplashAPI: UnsplashAPI,
    private val randomImageDao: RandomImageDao,
    private val imageCacheMapper: ImageCacheMapper,
    private val imageNetworkMapper: ImageNetworkMapper
) {


    fun getRandomPhoto(): Flow<PagingData<RandomImageCacheEntity>> {
        val pagingSource = { randomImageDao.getRandomImage() }
        Log.e("RandomImageDao", "${pagingSource}")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                prefetchDistance =10,
                initialLoadSize = 20
            ),
            remoteMediator = ImagePagingMediator(
                unsplashAPI,
                randomImageDao,
                imageCacheMapper,
                imageNetworkMapper
            ), pagingSourceFactory = pagingSource
        ).flow
    }


    suspend fun clearAllRandomImage(){
        randomImageDao.clearAll()
    }


}