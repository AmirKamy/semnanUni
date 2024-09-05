package com.semnan.semnanuniversity.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.data.model.NumberFilters
import com.semnan.semnanuniversity.network.ApiService

class NumberPagingSource(
    private val apiService: ApiService,
    private val filters: NumberFilters,
) : PagingSource<Int, Number>() {
    companion object {
        const val LIMIT = 10
        private const val INITIAL_LOAD_SIZE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Number>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Number> {
        return try {
            val position = params.key ?: INITIAL_LOAD_SIZE
            val offset = if (params.key != null) (position * LIMIT) else INITIAL_LOAD_SIZE

            Log.i("numbers1 req",offset.toString() + params.loadSize.toString() + filters.toString())

            val result = apiService.getNumbers(
                offset = offset,
                limit = params.loadSize,
                job_name = filters.job_name,
                subunit = filters.subunit,
                number = filters.number,
                address = filters.address,
            )

            Log.i("numbers1",result.toString())

            val nextKey = if (result.isEmpty()) {
                null
            } else {
                position + (params.loadSize / LIMIT)
            }
            val prevKey  = if (position == INITIAL_LOAD_SIZE) null else position - 1

            return LoadResult.Page(
                data = result,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}