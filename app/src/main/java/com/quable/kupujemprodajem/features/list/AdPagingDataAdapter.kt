package com.quable.kupujemprodajem.features.list

import androidx.recyclerview.widget.DiffUtil
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BasePagingDataAdapter
import com.quable.kupujemprodajem.features.AdView
import javax.inject.Inject

class AdPagingDataAdapter @Inject constructor() :
    BasePagingDataAdapter<AdListItemView>(AdComparator) {

    lateinit var onItemClicked: (AdView) -> Unit

    override fun provideLayoutId(position: Int) = when (getItem(position)!!) {
        is AdListItemView.AdItemView -> R.layout.item_ad
        is AdListItemView.HeaderItemView -> R.layout.item_header
    }

    override fun provideViewModel(position: Int) = when (val item = getItem(position)!!) {
        is AdListItemView.AdItemView -> AdListItemViewModel(item.data, onItemClicked)
        is AdListItemView.HeaderItemView -> AdListHeaderViewModel(item)
    }

    companion object {
        object AdComparator : DiffUtil.ItemCallback<AdListItemView>() {
            override fun areItemsTheSame(
                oldItem: AdListItemView,
                newItem: AdListItemView,
            ): Boolean {
                return when (oldItem) {
                    is AdListItemView.AdItemView -> if (newItem is AdListItemView.AdItemView) oldItem.data.id == newItem.data.id else false
                    is AdListItemView.HeaderItemView -> if (newItem is AdListItemView.HeaderItemView) oldItem.currentPage == newItem.currentPage else false
                }
            }

            override fun areContentsTheSame(
                oldItem: AdListItemView,
                newItem: AdListItemView,
            ): Boolean {
                return when (oldItem) {
                    is AdListItemView.AdItemView -> if (newItem is AdListItemView.AdItemView) oldItem.data == newItem.data else false
                    is AdListItemView.HeaderItemView -> if (newItem is AdListItemView.HeaderItemView) oldItem.currentPage == newItem.currentPage else false
                }
            }
        }
    }
}
