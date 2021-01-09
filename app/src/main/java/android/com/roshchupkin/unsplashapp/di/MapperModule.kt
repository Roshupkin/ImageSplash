package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.database.mapper.RandomImageCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.UrlsCacheMapper
import android.com.roshchupkin.unsplashapp.database.mapper.UserCacheMapper
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
    ): RandomImageCacheMapper = RandomImageCacheMapper(urlsCacheMapper, userCacheMapper)

}