package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.mapper.CollectionNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val unsplashAPI: UnsplashAPI,
    private val collectionNetworkMapper: CollectionNetworkMapper
) {

    fun getCollectionImage() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CollectionPagingSource(unsplashAPI, collectionNetworkMapper) }
        ).liveData
}