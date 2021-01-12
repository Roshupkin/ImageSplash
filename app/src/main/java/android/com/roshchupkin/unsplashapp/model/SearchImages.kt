package android.com.roshchupkin.unsplashapp.model

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.network.entity.image.ImageNetworkEntity

data class SearchImages (
    val total:Int,
    val total_pages:Int,
    val results: List<ImageDomain>
        )