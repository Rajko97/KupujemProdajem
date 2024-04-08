package com.quable.kupujemprodajem.features.single

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.base.observe
import com.quable.kupujemprodajem.databinding.FragmentAdPreviewBinding
import com.quable.kupujemprodajem.features.AdView
import com.quable.kupujemprodajem.features.list.AdListItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewAdFragment :
    BaseActionBarFragment<FragmentAdPreviewBinding, PreviewAdViewModel>() {

    private val args: PreviewAdFragmentArgs by navArgs()
    override fun provideLayoutId(): Int = R.layout.fragment_ad_preview

    override fun provideViewModelClass() = PreviewAdViewModel::class.java

    @SuppressLint("ClickableViewAccessibility")
    override fun setupUi() {
        binding.layoutId.vm = AdListItemViewModel(AdView())
        binding.layoutId.layoutItemAd.apply {
            isClickable = false
            isFocusable = false
            foreground = null
            isSoundEffectsEnabled = false
        }
        viewModel.initializeData(args.adId)

        SwipeGestureListener(requireContext()).apply {
            onSwipeLeft = viewModel::onSwipeLeft
            onSwipeRight = viewModel::onSwipeRight
        }.run {
            binding.layoutScroll.setOnTouchListener(this)
            binding.layoutEmptyContent.setOnTouchListener(this)
        }
    }

    override fun subscribeObservers() {
        observe(viewModel.adBasicLive) { binding.layoutId.vm = AdListItemViewModel(it) }
    }

    override fun onActionBack(): (() -> Unit) = {
        findNavController().popBackStack()
    }
}
