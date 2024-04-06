package com.quable.kupujemprodajem.features.list

import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.databinding.FragmentAdvertListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvertListFragment : BaseActionBarFragment<FragmentAdvertListBinding, AdvertListViewModel>() {

    override fun provideLayoutId(): Int = R.layout.fragment_advert_list

    override fun provideViewModelClass() = AdvertListViewModel::class.java

    override fun setupUi() {}

    override fun subscribeObservers() {}
}
