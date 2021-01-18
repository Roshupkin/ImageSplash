package android.com.roshchupkin.unsplashapp.repository.random

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.*
import retrofit2.HttpException
import java.io.IOException

private const val COUNT = 30

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
            if (randomImageDao.dbLength() > 120) {
                randomImageDao.clearFirst30()
            }


            val randomImageResponse = unsplashAPI.getRandomImage(COUNT)
            val listRandomImage = randomImageResponse
            val domainListRandomImage = imageNetworkMapper.mapFromEntityList(listRandomImage)
            val randomImageCache = imageCacheMapper.mapToEntityList(domainListRandomImage)

            when (loadType) {
                LoadType.REFRESH -> null

                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                }
            }





            randomImageDao.insertRandomImage(randomImageCache)
            val endOfPagnationReached = listRandomImage.isEmpty()
            return MediatorResult.Success(endOfPaginationReached = endOfPagnationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }

    }
}


