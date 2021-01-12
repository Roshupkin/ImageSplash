package android.com.roshchupkin.unsplashapp.model


data class ImageDomain(
    val description: String?,
    val height: Int?,
    val id: String,
    val urls: Urls?,
    val user: User?,
    val width: Int?
)