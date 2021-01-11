package android.com.roshchupkin.unsplashapp.network.service

import android.com.roshchupkin.unsplashapp.network.entity.collection.CollectionNetworkEntity
import android.com.roshchupkin.unsplashapp.network.entity.image.ImageNetworkEntity
import android.com.roshchupkin.unsplashapp.network.mapper.CollectionNetworkMapper
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashAPI {
    @Headers("Accept-Version: v1", "Authorization: Client-ID XXrCKFWKnVEBeZsoB7CdeX0kjAzwy4YHEirFDn2BFxo")
    @GET("photos/random")
    suspend fun getRandomImage(
        @Query("count") count: Int = 30
    ): List<ImageNetworkEntity>


    @Headers("Accept-Version: v1", "Authorization: Client-ID XXrCKFWKnVEBeZsoB7CdeX0kjAzwy4YHEirFDn2BFxo")
    @GET("photos/{id}")
    suspend fun getImageById(
         @Path("id")id: String
    ): ImageNetworkEntity

    @Headers("Accept-Version: v1", "Authorization: Client-ID XXrCKFWKnVEBeZsoB7CdeX0kjAzwy4YHEirFDn2BFxo")
    @GET("/collections")
    suspend fun getCollection(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int

    ): List<CollectionNetworkEntity>


}