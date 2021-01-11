package android.com.roshchupkin.unsplashapp.network.entity.image

import android.com.roshchupkin.unsplashapp.model.Image.ProfileImage

data class UserNetworkEntity(
    val name: String?,
    val username: String?,
    val profile_image: ProfileImageNetworkEntity?
)