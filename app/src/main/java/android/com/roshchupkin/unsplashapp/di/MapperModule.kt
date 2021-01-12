package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.database.mapper.ImageCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.ProfileImageCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.UrlsCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.UserCacheMapper
import android.com.roshchupkin.unsplashapp.network.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MapperModule {
    @Singleton
    @Provides
    fun provideRandomImageCacheMapper(
        urlsCacheMapper: UrlsCacheMapper,
        userCacheMapper: UserCacheMapper
    ): ImageCacheMapper = ImageCacheMapper(urlsCacheMapper, userCacheMapper)

    @Singleton
    @Provides
    fun provideImageNetworkMapper(
        urlsNetworkMapper: UrlsNetworkMapper,
        userNetworkMapper: UserNetworkMapper
    ): ImageNetworkMapper = ImageNetworkMapper(urlsNetworkMapper, userNetworkMapper)

    @Singleton
    @Provides
    fun provideCoverPhotoNetworkMapper(
        urlsNetworkMapper: UrlsNetworkMapper
    ): CoverPhotoNetworkMapper = CoverPhotoNetworkMapper(urlsNetworkMapper)

    @Singleton
    @Provides
    fun provideCollectionNetworkMapper(
        coverPhotoNetworkMapper: CoverPhotoNetworkMapper,
        userNetworkMapper: UserNetworkMapper
    ): CollectionNetworkMapper = CollectionNetworkMapper(coverPhotoNetworkMapper, userNetworkMapper)

    @Singleton
    @Provides
    fun provideUserNetworkMapper(
        profileImageNetworkMapper: ProfileImageNetworkMapper
    ): UserNetworkMapper = UserNetworkMapper(profileImageNetworkMapper)

    @Singleton
    @Provides
    fun provideUserCacheMapper(
        profileImageCacheMapper: ProfileImageCacheMapper
    ): UserCacheMapper = UserCacheMapper(profileImageCacheMapper)

    @Singleton
    @Provides
    fun provideSearchImagesMapper(
        imageNetworkMapper: ImageNetworkMapper
    ): SearchImagesNetworkMapper = SearchImagesNetworkMapper(imageNetworkMapper)
}