package android.com.roshchupkin.unsplashapp.database

import android.com.roshchupkin.unsplashapp.database.dao.RandomImageDao
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import androidx.room.Database
import androidx.room.RoomDatabase

//@TypeConverters(Converters::class)
@Database(
    entities = [RandomImageCacheEntity::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun randomInageDao(): RandomImageDao

    companion object {
        const val DATABASE_NAME: String = "iamge_database"
    }


}