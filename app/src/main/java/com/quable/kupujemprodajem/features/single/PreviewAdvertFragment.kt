package com.quable.kupujemprodajem.features.single

import android.widget.Toast
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.databinding.FragmentAdvertPreviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewAdvertFragment :
    BaseActionBarFragment<FragmentAdvertPreviewBinding, PreviewAdvertViewModel>() {
    override fun provideLayoutId(): Int = R.layout.fragment_advert_preview

    override fun provideViewModelClass() = PreviewAdvertViewModel::class.java

    override fun setupUi() {}

    override fun subscribeObservers() {}

    override fun onActionBack(): (() -> Unit) = {
        Toast.makeText(requireContext(), "Clicked!", Toast.LENGTH_SHORT).show()
    }
}
