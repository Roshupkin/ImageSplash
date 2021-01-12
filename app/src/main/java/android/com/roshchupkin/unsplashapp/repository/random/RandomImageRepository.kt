package android.com.roshchupkin.unsplashapp.repository.random

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.model.Urls
import android.com.roshchupkin.unsplashapp.model.User
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RandomImageRepository(
    private val unsplashAPI: UnsplashAPI,
    private val randomImageDao: RandomImageDao,
    private val imageCacheMapper: ImageCacheMapper,
    private val imageNetworkMapper: ImageNetworkMapper
) {


    fun getRandomPhoto(): Flow<PagingData<ImageDomain>> {
        val pagingSource = { randomImageDao.getRandomImage() }
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
        ).flow.map { pagingData: PagingData<RandomImageCacheEntity> -> pagingData.map { randomImageCacheEntity: RandomImageCacheEntity ->
            imageCacheMapper.mapFromEntity(randomImageCacheEntity)
        } }
    }


    suspend fun clearAllRandomImage(){
        randomImageDao.clearAll()
    }




}