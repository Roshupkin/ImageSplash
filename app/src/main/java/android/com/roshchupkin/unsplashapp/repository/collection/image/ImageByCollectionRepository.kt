package android.com.roshchupkin.unsplashapp.repository.collection.image

import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class ImageByCollectionRepository
@Inject
constructor(
    private val unsplashAPI: UnsplashAPI,
    private val imageNetworkMapper: ImageNetworkMapper
) {
    fun getListOfImagesById(id: Int) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImageByCollectionPagingSource(
                    id,
                    unsplashAPI,
                    imageNetworkMapper
                ) }
        ).liveData
}