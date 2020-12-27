package android.com.roshchupkin.unsplashapp.database.dao

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RandomImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRandomImage(randomImageCache: RandomImageCacheEntity): Long

    @Query("SELECT * FROM random_image ")
    suspend fun getRandomImage(): RandomImageCacheEntity
}