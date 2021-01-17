package android.com.roshchupkin.unsplashapp.network.entity

import android.com.roshchupkin.unsplashapp.model.*
import com.google.gson.annotations.SerializedName

data class CollectionNetworkEntity(
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("total_photos") var totalPhotos: Int?,
    @SerializedName("cover_photo") var coverPhoto: CoverPhotoNetworkEntity?,
    @SerializedName("user") var user: UserNetworkEntity?,
)

