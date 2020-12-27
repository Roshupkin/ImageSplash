package android.com.roshchupkin.unsplashapp.network.entity

data class RandomImageNetworkEntity(
    val description: String,
    val height: Int,
    val id: String,
    val urlsNetworkEntity: UrlsNetworkEntity,
    val userNetworkEntity: UserNetworkEntity,
    val width: Int
)