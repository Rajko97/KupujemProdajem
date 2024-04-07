package com.quable.kupujemprodajem.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.quable.domain.usecases.GetAds
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdListViewModel @Inject constructor(
    getAds: GetAds,
) : ViewModel() {
    val flow = Pager(
        PagingConfig(
            initialLoadSize = 10,
            pageSize = 11,
        ),
    ) {
        AdPagingSource(getAds)
    }.flow
        .cachedIn(viewModelScope)
}
