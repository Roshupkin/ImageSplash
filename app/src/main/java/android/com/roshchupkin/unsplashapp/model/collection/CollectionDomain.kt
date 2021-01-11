package android.com.roshchupkin.unsplashapp.model.collection

import android.com.roshchupkin.unsplashapp.model.Image.User

data class CollectionDomain(
    var id: Int?,
    var title: String?,
    var totalPhotos: Int?,
    var coverPhoto: CoverPhoto?,
    var user: User?
)
