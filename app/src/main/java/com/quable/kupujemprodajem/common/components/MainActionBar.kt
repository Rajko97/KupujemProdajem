package com.quable.kupujemprodajem.common.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.quable.kupujemprodajem.databinding.ActionBarMainBinding

class MainActionBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ActionBarMainBinding.inflate(LayoutInflater.from(context), this, true)
    private var onActionBack: (() -> Unit)? = null

    init {
        binding.btnActionBack.visibility = View.GONE
        binding.btnActionBack.setOnClickListener {
            onActionBack?.invoke()
        }
    }

    fun setOnActionBack(onActionBack: (() -> Unit)?) {
        this.onActionBack = onActionBack
        binding.btnActionBack.visibility = if (onActionBack == null) View.GONE else View.VISIBLE
    }
}
