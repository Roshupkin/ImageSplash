package android.com.roshchupkin.unsplashapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "random_image")
data class RandomImageCacheEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String,
    val height: Int,
    val width: Int,
    val urlsImageFull:String,
    val username:String,
)