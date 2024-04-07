package com.quable.kupujemprodajem.features.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quable.domain.usecases.GetAds
import com.quable.kupujemprodajem.features.toView

class AdPagingSource(
    private val getAds: GetAds,
) : PagingSource<Int, AdListItemView>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdListItemView> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = getAds.execute(nextPageNumber)

            val data = response.ads.map { AdListItemView.AdItemView(it.toView()) }

            LoadResult.Page(
                data = if (nextPageNumber > 1) {
                    attachHeaderToList(
                        data,
                        response.page,
                        response.pages,
                    )
                } else {
                    data
                },
                prevKey = if (response.page > 0) response.page - 1 else null,
                nextKey = if (response.page < 12) response.page + 1 else null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AdListItemView>): Int? = null

    private fun attachHeaderToList(
        data: List<AdListItemView.AdItemView>,
        page: Int,
        pages: Int,
    ): List<AdListItemView> {
        return mutableListOf<AdListItemView>().apply {
            add(AdListItemView.HeaderItemView(page, pages))
            addAll(data)
        }
    }
}
