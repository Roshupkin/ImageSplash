package android.com.roshchupkin.unsplashapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton


@InstallIn(ApplicationComponentManager::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "Its some string!"
    }
}