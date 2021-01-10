package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.com.roshchupkin.unsplashapp.repository.DetailImageRepository
import android.com.roshchupkin.unsplashapp.repository.RandomImageRepository
import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRundomPhotoRepository(
        unsplashAPI: UnsplashAPI,
        randomImageDao: RandomImageDao,
        imageCacheMapper: ImageCacheMapper
    ): RandomImageRepository =
        RandomImageRepository(
            unsplashAPI,
            randomImageDao,
            imageCacheMapper
        )

    @Singleton
    @Provides
    fun provideImageByIdRepository(
        unsplashAPI: UnsplashAPI,
        imageNetworkMapper:ImageNetworkMapper

    ):DetailImageRepository = DetailImageRepository(unsplashAPI,imageNetworkMapper)



}