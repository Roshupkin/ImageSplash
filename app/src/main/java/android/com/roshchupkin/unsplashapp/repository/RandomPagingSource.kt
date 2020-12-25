package android.com.roshchupkin.unsplashapp.repository

import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class RandomPagingSource(private val unsplashAPI: UnsplashAPI) :
    PagingSource<Int, RandomPhotoListResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomPhotoListResponse> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashAPI.getRandomPhoto()

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