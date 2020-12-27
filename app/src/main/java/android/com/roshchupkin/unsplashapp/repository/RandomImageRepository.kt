package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

class RandomImageRepository(
    private val unsplashAPI: UnsplashAPI
) {
fun getRandomPhoto()=Pager(
    config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ),
    pagingSourceFactory = { RandomPagingSource(unsplashAPI) }
).liveData

}