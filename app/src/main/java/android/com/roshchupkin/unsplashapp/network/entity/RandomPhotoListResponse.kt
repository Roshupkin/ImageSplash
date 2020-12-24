package android.com.roshchupkin.unsplashapp.network.entity

data class RandomPhotoListResponse(
    val description: String,
    val height: Int,
    val id: String,
    val urls: Urls,
    val user: User,
    val width: Int
)