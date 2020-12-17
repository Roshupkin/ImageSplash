package android.com.roshchupkin.unsplashapp.network.service

import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface UnsplashAPI {
    @Headers("Authorization:", "7wZDrw0B0aYLZn03O3AWndMHBiGYsRSxhYl3hHMcTo4")
    @GET("photos/random")
    suspend fun getRandomPhoto(): RandomPhotoEntity
}