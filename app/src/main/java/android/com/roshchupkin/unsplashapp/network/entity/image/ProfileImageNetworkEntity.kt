package android.com.roshchupkin.unsplashapp.network.entity.image

import com.google.gson.annotations.SerializedName

data class ProfileImageNetworkEntity(
    @SerializedName("small") var small: String?,
    @SerializedName("medium") var medium: String?,
    @SerializedName("large") var large: String?
)
