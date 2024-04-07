package com.quable.kupujemprodajem.features.single

import androidx.navigation.fragment.findNavController
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.databinding.FragmentAdvertPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewAdFragment :
    BaseActionBarFragment<FragmentAdvertPreviewBinding, PreviewAdViewModel>() {
    override fun provideLayoutId(): Int = R.layout.fragment_advert_preview

    override fun provideViewModelClass() = PreviewAdViewModel::class.java

    override fun setupUi() {}

    override fun subscribeObservers() {}

    override fun onActionBack(): (() -> Unit) = {
        findNavController().popBackStack()
    }
}
