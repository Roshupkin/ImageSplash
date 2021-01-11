package android.com.roshchupkin.unsplashapp.network.entity.collection

import android.com.roshchupkin.unsplashapp.network.entity.image.UrlsNetworkEntity
import com.google.gson.annotations.SerializedName

data class CoverPhotoNetworkEntity (
    @SerializedName("urls") var urls : UrlsNetworkEntity?,
        )