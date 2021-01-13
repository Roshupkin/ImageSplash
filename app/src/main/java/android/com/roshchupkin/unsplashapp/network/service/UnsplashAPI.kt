package android.com.roshchupkin.unsplashapp.network.service

import android.com.roshchupkin.unsplashapp.network.entity.SearchImagesNetworkEntity
import android.com.roshchupkin.unsplashapp.network.entity.CollectionNetworkEntity
import android.com.roshchupkin.unsplashapp.network.entity.ImageNetworkEntity
import android.com.roshchupkin.unsplashapp.repository.collection.image.ImageByCollectionRepository
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashAPI {
    @Headers("Accept-Version: v1", "Authorization: Client-ID jkr2afOninca7fO5MuE6RrjMjuYCEWB76Q20Ry5GCQA")
    @GET("photos/random")
    suspend fun getRandomImage(
        @Query("count") count: Int
    ): List<ImageNetworkEntity>


    @Headers("Accept-Version: v1", "Authorization: Client-ID jkr2afOninca7fO5MuE6RrjMjuYCEWB76Q20Ry5GCQA")
    @GET("photos/{id}")
    suspend fun getImageById(
         @Path("id")id: String
    ): ImageNetworkEntity

    @Headers("Accept-Version: v1", "Authorization: Client-ID jkr2afOninca7fO5MuE6RrjMjuYCEWB76Q20Ry5GCQA")
    @GET("/collections")
    suspend fun getCollection(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int

    ): List<CollectionNetworkEntity>

    @Headers("Accept-Version: v1", "Authorization: Client-ID jkr2afOninca7fO5MuE6RrjMjuYCEWB76Q20Ry5GCQA")
    @GET("/collections/{id}/photos")
    suspend fun getImagesByCollection(
        @Path("id")id: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ImageNetworkEntity>

    @Headers("Accept-Version: v1", "Authorization: Client-ID jkr2afOninca7fO5MuE6RrjMjuYCEWB76Q20Ry5GCQA")
    @GET("/search/photos")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchImagesNetworkEntity
}