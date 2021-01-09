package android.com.roshchupkin.unsplashapp.model

import android.util.Log


data class RandomImage(
    val description: String?,
    val height: Int?,
    val id: String,
    val urls: Urls?,
    val user: User?,
    val width: Int?
)