package android.com.roshchupkin.unsplashapp.model

data class SearchImages (
    val total:Int,
    val total_pages:Int,
    val results: List<ImageDomain>
        )