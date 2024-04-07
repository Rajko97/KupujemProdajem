package com.quable.kupujemprodajem.features.list

import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.quable.kupujemprodajem.R
import com.quable.kupujemprodajem.base.BaseActionBarFragment
import com.quable.kupujemprodajem.databinding.FragmentAdListBinding
import com.quable.kupujemprodajem.features.AdView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AdListFragment : BaseActionBarFragment<FragmentAdListBinding, AdListViewModel>() {

    @Inject
    lateinit var pagingAdapter: AdPagingDataAdapter
    override fun provideLayoutId(): Int = R.layout.fragment_ad_list

    override fun provideViewModelClass() = AdListViewModel::class.java

    override fun setupUi() {
        pagingAdapter.onItemClicked = { this@AdListFragment.onItemClicked(it) }

        binding.recyclerAds.apply {
            layoutManager = LinearLayoutManager(context).also {
                val itemDecoration = DividerItemDecoration(context, it.orientation).apply {
                    ContextCompat.getDrawable(context, R.drawable.bg_horizontal_line)
                        ?.let { drawable ->
                            setDrawable(drawable)
                        }
                }

                addItemDecoration(itemDecoration)
            }
            adapter = pagingAdapter
        }
    }

    override fun subscribeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun onItemClicked(item: AdView) {
        findNavController().navigate(
            AdListFragmentDirections.actionAdListFragmentToPreviewAdFragment(item),
        )
    }
}
