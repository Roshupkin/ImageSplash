package android.com.roshchupkin.unsplashapp.database.dao

import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
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

    @Query("DELETE FROM random_image WHERE rowid IN (SELECT rowid FROM random_image LIMIT 30)")
    suspend fun clearFirst30()

    @Query("SELECT COUNT(*) FROM random_image")
    suspend fun dbLength():Int
}