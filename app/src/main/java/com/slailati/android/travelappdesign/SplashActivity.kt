package com.slailati.android.travelappdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.lifecycle.lifecycleScope
import com.slailati.android.travelappdesign.customview.LoadingButton
import com.slailati.android.travelappdesign.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignIn.run {
            setOnClickListener {
                fakeSignInProcess()
            }
        }
    }

    private fun LoadingButton.fakeSignInProcess() {
        initLoading()
        lifecycleScope.launch {
            delay(4_000)
            stopLoading()
        }
    }
}