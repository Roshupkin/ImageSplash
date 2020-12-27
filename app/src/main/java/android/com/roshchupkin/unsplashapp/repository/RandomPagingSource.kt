package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import android.util.Log
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class RandomPagingSource(private val unsplashAPI: UnsplashAPI) :
    PagingSource<Int, RandomImageNetworkEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomImageNetworkEntity> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        val TAG = "UnsplashSourse"
        return try {
            val response = unsplashAPI.getRandomPhoto()
            Log.e(
                TAG,
                " PARAMS ${params.key}   PARAMS SIZE ${params.loadSize}    POSITION$position  prevKey ${if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1}  nextKey ${if (response.isEmpty()) null else position + 1}   "
            )
            LoadResult.Page(
                data = response,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}