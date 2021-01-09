package android.com.roshchupkin.unsplashapp.network.entity

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.annotations.SerializedName


data class RandomImageNetworkEntity(
    val description: String?,
    val height: Int?,
    val id: String,
    val urls: UrlsNetworkEntity?,
    val user: UserNetworkEntity?,
    val width: Int?
)