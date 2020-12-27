package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.database.DataBase
import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context, DataBase::class.java,
            DataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomImageDao(dataBase: DataBase): RandomImageDao = dataBase.randomInageDao()


}