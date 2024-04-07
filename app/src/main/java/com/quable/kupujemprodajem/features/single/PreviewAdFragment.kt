package com.quable.kupujemprodajem.features.single

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.databinding.FragmentAdPreviewBinding
import com.quable.kupujemprodajem.features.list.AdListItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewAdFragment :
    BaseActionBarFragment<FragmentAdPreviewBinding, PreviewAdViewModel>() {

    private val args: PreviewAdFragmentArgs by navArgs()
    override fun provideLayoutId(): Int = R.layout.fragment_ad_preview

    override fun provideViewModelClass() = PreviewAdViewModel::class.java

    override fun setupUi() {
        binding.layoutId.vm = AdListItemViewModel(args.adView)
        binding.layoutId.layoutItemAd.apply {
            isClickable = false
            isFocusable = false
            foreground = null
            isSoundEffectsEnabled = false
        }
        viewModel.initializeData(args.adView.id.toString())
    }

    override fun subscribeObservers() {}

    override fun onActionBack(): (() -> Unit) = {
        findNavController().popBackStack()
    }
}
