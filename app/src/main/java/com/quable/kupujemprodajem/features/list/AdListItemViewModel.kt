package com.quable.kupujemprodajem.features.list

import androidx.lifecycle.ViewModel
import com.quable.kupujemprodajem.features.AdView

class AdListItemViewModel(
    val item: AdView,
    private val onItemClicked: ((AdView) -> Unit)? = null,
) : ViewModel() {

    fun onItemClicked() {
        onItemClicked?.invoke(item)
    }
}
