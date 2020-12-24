package android.com.roshchupkin.unsplashapp.network.service

import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashAPI {
    @Headers("Accept-Version: v1", "Authorization: Client-ID 7wZDrw0B0aYLZn03O3AWndMHBiGYsRSxhYl3hHMcTo4")
    @GET("photos/random")
    suspend fun getRandomPhoto(
        @Query("count") count: Int = 10
    ): List<RandomPhotoListResponse>
}