package android.com.roshchupkin.unsplashapp.repository.collection.image

import android.com.roshchupkin.unsplashapp.model.ImageDomain
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
            val responseImageByCollection = unsplashAPI.getImagesByCollection(imageListId,position,params.loadSize)/*.response*/
            val images = imageNetworkMapper.mapFromEntityList(responseImageByCollection)

            LoadResult.Page(
                data = images,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (images.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}