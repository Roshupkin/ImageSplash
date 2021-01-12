package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.network.mapper.SearchImagesNetworkMapper
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.util.Log
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class SearchImagePagingSource(
    private val queryString: String,
    private val unsplashAPI: UnsplashAPI,
    private val searchImagesNetworkMapper: SearchImagesNetworkMapper
) : PagingSource<Int, ImageDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDomain> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            Log.e("LOADING","${params.loadSize}")
            val response = unsplashAPI.searchImage(queryString,position,params.loadSize )
            val images = searchImagesNetworkMapper.mapFromEntity(response)

            LoadResult.Page(
                data = images.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (images.results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}