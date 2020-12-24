package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.com.roshchupkin.unsplashapp.repository.RandomPhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRundomPhotoRepository(unsplashAPI: UnsplashAPI): RandomPhotoRepository =
        RandomPhotoRepository(unsplashAPI)
}