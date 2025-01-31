package com.haris.core.data.network

import retrofit2.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haris.core.domain.model.People
import com.haris.core.utils.PeopleMapper
import java.io.IOException
import java.net.SocketTimeoutException

class PeoplePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position = params.key ?: 1

        return try {
            val response = apiService.getPeople(page = position)

            val peopleList = response.results.orEmpty()
                .mapNotNull { it?.let { PeopleMapper.mapResultsItemToPeople(it) } }

            LoadResult.Page(
                data = peopleList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.next == null) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: SocketTimeoutException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
