package com.quable.kupujemprodajem.features.list

import androidx.lifecycle.ViewModel
import com.quable.kupujemprodajem.features.AdView

class AdListItemViewModel(
    val item: AdView,
    private val onItemClicked: (Long) -> Unit,
) : ViewModel() {

    fun onItemClicked() {
        onItemClicked.invoke(item.id)
    }
}
