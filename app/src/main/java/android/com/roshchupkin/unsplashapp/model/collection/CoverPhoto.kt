package android.com.roshchupkin.unsplashapp.model.collection

import android.com.roshchupkin.unsplashapp.model.Image.Urls
import android.com.roshchupkin.unsplashapp.network.entity.image.UrlsNetworkEntity

data class CoverPhoto(
    var urls: Urls?
)
