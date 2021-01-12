package android.com.roshchupkin.unsplashapp.network.entity

import android.com.roshchupkin.unsplashapp.network.entity.image.ImageNetworkEntity

data class SearchImagesNetworkEntity (
    val total:Int,
    val total_pages:Int,
    val results: List<ImageNetworkEntity>
        )