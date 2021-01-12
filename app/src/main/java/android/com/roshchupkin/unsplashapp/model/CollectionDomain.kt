package android.com.roshchupkin.unsplashapp.model

data class CollectionDomain(
    var id: Int?,
    var title: String?,
    var totalPhotos: Int?,
    var coverPhoto: CoverPhoto?,
    var user: User?
)
