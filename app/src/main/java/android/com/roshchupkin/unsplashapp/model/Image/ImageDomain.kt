package android.com.roshchupkin.unsplashapp.model.Image


data class ImageDomain(
    val description: String?,
    val height: Int?,
    val id: String,
    val urls: Urls?,
    val user: User?,
    val width: Int?
)