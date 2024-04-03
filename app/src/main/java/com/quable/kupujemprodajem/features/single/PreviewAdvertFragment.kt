package com.quable.kupujemprodajem.features.single

import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseFragment
import com.quable.kupujemprodajem.databinding.FragmentAdvertPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewAdvertFragment : BaseFragment<FragmentAdvertPreviewBinding, PreviewAdvertViewModel>() {
    override fun provideLayoutId(): Int = R.layout.fragment_advert_preview

    override fun provideViewModelClass() = PreviewAdvertViewModel::class.java

    override fun setupUi() {}

    override fun subscribeObservers() {}
}
