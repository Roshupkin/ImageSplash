package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.model.collection.CollectionDomain
import android.com.roshchupkin.unsplashapp.network.mapper.CollectionNetworkMapper
import android.com.roshchupkin.unsplashapp.network.mapper.ImageNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
class ImageByCollectionPagingSource(
    private val imageListId:Int,
    private val unsplashAPI: UnsplashAPI,
    private val imageNetworkMapper: ImageNetworkMapper
) : PagingSource<Int, ImageDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDomain> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = unsplashAPI.getImageCollection(imageListId,position,params.loadSize)
            val photos = imageNetworkMapper.mapFromEntityList(response)

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