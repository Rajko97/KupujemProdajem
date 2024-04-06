package com.quable.kupujemprodajem.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.common.components.MainActionBar

abstract class BaseActionBarFragment<BindingT : ViewDataBinding, ViewModelT : ViewModel> :
    BaseFragment<BindingT, ViewModelT>() {
    open fun onActionBack(): (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            with(binding.root.findViewById<MainActionBar>(R.id.action_bar_main)) {
                setOnActionBack(onActionBack())
            }
        }
    }
}
