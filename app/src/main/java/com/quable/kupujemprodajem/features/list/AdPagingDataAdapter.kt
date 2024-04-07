package com.quable.kupujemprodajem.features.list

import androidx.recyclerview.widget.DiffUtil
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BasePagingDataAdapter
import com.quable.kupujemprodajem.features.AdView
import javax.inject.Inject

class AdPagingDataAdapter @Inject constructor() :
    BasePagingDataAdapter<AdView>(AdComparator) {

    lateinit var onItemClicked: (Long) -> Unit

    override fun provideLayoutId(position: Int) = R.layout.item_add

    override fun provideViewModel(position: Int) =
        AdListItemViewModel(getItem(position)!!, onItemClicked)

    companion object {
        object AdComparator : DiffUtil.ItemCallback<AdView>() {
            override fun areItemsTheSame(oldItem: AdView, newItem: AdView): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AdView, newItem: AdView): Boolean {
                return oldItem.posted == newItem.posted &&
                    oldItem.locationName == newItem.locationName &&
                    oldItem.name == newItem.name &&
                    oldItem.price == newItem.price &&
                    oldItem.currency == newItem.currency &&
                    oldItem.priceFixed == newItem.priceFixed &&
                    oldItem.photoThumbnailUrl == newItem.photoThumbnailUrl &&
                    oldItem.favoriteCount == newItem.favoriteCount &&
                    oldItem.isFollowingAd == newItem.isFollowingAd
            }
        }
    }
}
