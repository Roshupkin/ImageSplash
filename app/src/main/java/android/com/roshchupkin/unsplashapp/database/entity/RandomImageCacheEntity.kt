package android.com.roshchupkin.unsplashapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "random_image")
data class RandomImageCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("urlsImageRegular")
    val urlsImageRegular:String?,
    @SerializedName("username")
    val name:String?,
    @SerializedName("profileImageSmall")
    var profileImageSmall: String?

)