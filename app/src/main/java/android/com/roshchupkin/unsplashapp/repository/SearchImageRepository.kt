package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.mapper.SearchImagesNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class SearchImageRepository
@Inject
constructor(
    private val unsplashAPI: UnsplashAPI,
    private val searchImagesNetworkMapper: SearchImagesNetworkMapper
) {
    fun searchImage(qurey: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
               SearchImagePagingSource(
                    qurey,
                    unsplashAPI,
                   searchImagesNetworkMapper
                )
            }
        ).liveData
}