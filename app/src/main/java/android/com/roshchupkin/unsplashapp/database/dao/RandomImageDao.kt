package android.com.roshchupkin.unsplashapp.database.dao

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RandomImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRandomImage(randomImageNetworkEntity: List<RandomImageCacheEntity>)

    @Query("SELECT * FROM random_image ")
    fun getRandomImage(): PagingSource<Int, RandomImageCacheEntity>

    @Query("DELETE FROM random_image")
    suspend fun clearAll()
}