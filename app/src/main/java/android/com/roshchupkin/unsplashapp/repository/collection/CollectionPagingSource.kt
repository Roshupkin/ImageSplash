package android.com.roshchupkin.unsplashapp.repository.collection

import android.com.roshchupkin.unsplashapp.model.CollectionDomain
import android.com.roshchupkin.unsplashapp.network.mapper.CollectionNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
class CollectionPagingSource(
    private val unsplashAPI: UnsplashAPI,
    private val collectionNetworkMapper: CollectionNetworkMapper
) : PagingSource<Int, CollectionDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionDomain> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val responseCollections = unsplashAPI.getCollection(position,params.loadSize)
            val photos = collectionNetworkMapper.mapFromEntityList(responseCollections)

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}