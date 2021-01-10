package android.com.roshchupkin.unsplashapp.network.service

import android.com.roshchupkin.unsplashapp.network.entity.ImageNetworkEntity
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.util.Log
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashAPI {
    @Headers("Accept-Version: v1", "Authorization: Client-ID BvAOjlA1CiId__0QwO7W68nQNcFlkaVlHT4MoRISKUE")
    @GET("photos/random")
    suspend fun getRandomImage(
        @Query("count") count: Int = 30
    ): List<RandomImageNetworkEntity>


    @Headers("Accept-Version: v1", "Authorization: Client-ID BvAOjlA1CiId__0QwO7W68nQNcFlkaVlHT4MoRISKUE")
    @GET("photos/{id}")
    suspend fun getImageById(
         @Path("id")id: String
    ): RandomImageNetworkEntity


}