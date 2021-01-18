package android.com.roshchupkin.unsplashapp.network.entity

import com.google.gson.annotations.SerializedName

data class CollectionNetworkEntity(
    var id: Int?,
    var title: String?,
    var total_photos: Int?,
    var cover_photo: CoverPhotoNetworkEntity?,
    var user: UserNetworkEntity?,
)
