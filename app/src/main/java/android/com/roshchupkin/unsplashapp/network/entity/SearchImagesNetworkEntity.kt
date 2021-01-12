package android.com.roshchupkin.unsplashapp.network.entity

data class SearchImagesNetworkEntity (
    val total:Int,
    val total_pages:Int,
    val results: List<ImageNetworkEntity>
        )