package com.quable.kupujemprodajem.features.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quable.domain.usecases.GetAds
import com.quable.kupujemprodajem.features.AdView
import com.quable.kupujemprodajem.features.toView

class AdPagingSource(
    private val getAds: GetAds,
) : PagingSource<Int, AdView>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdView> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = getAds.execute(nextPageNumber)

            val data = response.ads

            LoadResult.Page(
                data = data.map { it.toView() },
                prevKey = if (response.page > 0) response.page - 1 else null,
                nextKey = if (response.page < 10) response.page + 1 else null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AdView>): Int? = null
}
