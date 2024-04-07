package com.quable.kupujemprodajem.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.quable.kupujemprodajem.BR

class BindingViewHolder(
    private val binding: ViewDataBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindViewModel(viewModel: ViewModel) {
        binding.apply {
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }
}
