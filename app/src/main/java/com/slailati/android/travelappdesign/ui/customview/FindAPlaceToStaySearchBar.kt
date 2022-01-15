package com.slailati.android.travelappdesign.ui.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.slailati.android.travelappdesign.R
import com.slailati.android.travelappdesign.databinding.CustomFindAPlaceToStaySearchBarBinding
import kotlin.math.hypot

class FindAPlaceToStaySearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var isSearching: Boolean = false
    private var titleHint: String = ""

    private var state: FindAPlaceSearchBarState = FindAPlaceSearchBarState.Empty
        set(value) {
            field = value
            updateState()
        }

    private val binding = CustomFindAPlaceToStaySearchBarBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        setLayout(attrs)
    }

    private fun updateState() {
        refreshDrawableState()

        if (state.backIconVisibility == View.VISIBLE)
            showBackIconAnimated()
        else if (state.backIconVisibility == View.GONE)
            hideBackIconAnimated()

        if (state.isSearching) {
            binding.ivSearchIcon.visibility = View.INVISIBLE
            binding.lavSearchingAnimation.visibility = View.VISIBLE
        } else {
            binding.ivSearchIcon.visibility = View.VISIBLE
            binding.lavSearchingAnimation.visibility = View.INVISIBLE
        }
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.FindAPlaceToStaySearchBar
            )

            isSearching =
                attributes.getBoolean(R.styleable.FindAPlaceToStaySearchBar_is_searching, false)
            titleHint = attributes.getString(R.styleable.FindAPlaceToStaySearchBar_title_hint) ?: ""

            binding.etSearchBar.apply {
                hint = titleHint
                doOnTextChanged { text, _, _, _ ->
                    if (!text.isNullOrEmpty() && state == FindAPlaceSearchBarState.Empty)
                        state = FindAPlaceSearchBarState.Searching
                }
                onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                    if (!hasFocus) {
                        hideKeyboard(v)
                    }
                }
            }

            binding.ivBackIcon.setOnClickListener {
                binding.etSearchBar.apply {
                    clearFocus()
                    setText("")
                }
                state = FindAPlaceSearchBarState.Empty
            }

            attributes.recycle()
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager: InputMethodManager? =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setIsSearching(isSearching: Boolean) {
        state = if (isSearching)
            FindAPlaceSearchBarState.Searching
        else
            FindAPlaceSearchBarState.Empty
    }

    sealed class FindAPlaceSearchBarState(
        val isSearching: Boolean,
        val backIconVisibility: Int
    ) {
        object Searching : FindAPlaceSearchBarState(
            isSearching = true,
            backIconVisibility = View.VISIBLE
        )

        object Empty : FindAPlaceSearchBarState(
            isSearching = false,
            backIconVisibility = View.GONE
        )
    }

    private fun showBackIconAnimated() {
        val cx = binding.ivBackIcon.width / 2
        val cy = binding.ivBackIcon.height / 2

        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim =
            ViewAnimationUtils.createCircularReveal(binding.ivBackIcon, cx, cy, 0f, finalRadius)
        anim.duration = 600

        binding.ivBackIcon.visibility = View.VISIBLE
        anim.start()
    }

    private fun hideBackIconAnimated() {
        val cx = binding.ivBackIcon.width / 2
        val cy = binding.ivBackIcon.height / 2

        val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim =
            ViewAnimationUtils.createCircularReveal(binding.ivBackIcon, cx, cy, initialRadius, 0f)
        anim.duration = 600

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                binding.ivBackIcon.visibility = View.INVISIBLE
            }
        })

        anim.start()
    }

}