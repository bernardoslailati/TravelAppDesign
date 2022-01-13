package com.slailati.android.travelappdesign.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.slailati.android.travelappdesign.R
import com.slailati.android.travelappdesign.databinding.CustomLoadingButtonBinding

class LoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var title: String = ""
    private var titleSize: Float = 18f
    private var isLoading: Boolean = false

    private val binding =
        CustomLoadingButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    private var state: LoadingButtonState = LoadingButtonState.Normal
        set(value) {
            field = value
            updateState()
        }

    private fun updateState() {
        binding.clContent.isEnabled = state.isEnabled
        refreshDrawableState()

        binding.pbLoading.visibility = state.loadingVisibility

        when (state) {
            LoadingButtonState.Normal -> binding.tvTitle.text = title
            LoadingButtonState.Loading -> binding.tvTitle.text = ""
        }
    }

    init {
        setLayout(attrs)
        updateState()
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.LoadingButton
            )

            isLoading = attributes.getBoolean(R.styleable.LoadingButton_is_loading, false)
            title = attributes.getString(R.styleable.LoadingButton_title) ?: ""
            titleSize = attributes.getDimension(R.styleable.LoadingButton_title_size, 18f)

            binding.tvTitle.textSize = titleSize

            attributes.recycle()
        }
    }

    fun initLoading() {
        state = LoadingButtonState.Loading
    }

    fun stopLoading() {
        state = LoadingButtonState.Normal
    }

    fun getIsLoading() = state.isEnabled

    sealed class LoadingButtonState (val isEnabled: Boolean, val loadingVisibility: Int) {
        object Normal: LoadingButtonState(true, View.INVISIBLE)
        object Loading: LoadingButtonState(false, View.VISIBLE)
    }

}