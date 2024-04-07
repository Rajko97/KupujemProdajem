package com.quable.kupujemprodajem.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagingDataAdapter<ItemT : Any>(diffUtilItemCallback: DiffUtil.ItemCallback<ItemT>) :
    PagingDataAdapter<ItemT, BindingViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) =
        holder.bindViewModel(provideViewModel(position))

    override fun getItemViewType(position: Int) = provideLayoutId(position)

    @LayoutRes
    abstract fun provideLayoutId(position: Int): Int

    abstract fun provideViewModel(position: Int): ViewModel
}
