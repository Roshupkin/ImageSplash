package android.com.roshchupkin.unsplashapp.model

import android.com.roshchupkin.unsplashapp.model.ProfileImage

data class User(
    val name: String?,
    val username: String?,
    val profile_image: ProfileImage?
)