package com.quable.kupujemprodajem.features.list

import com.quable.kupujemprodajem.features.AdView

sealed class AdListItemView {
    class AdItemView(
        val data: AdView,
    ) : AdListItemView()

    data class HeaderItemView(
        val currentPage: Int,
        val totalPages: Int,
    ) : AdListItemView()
}
