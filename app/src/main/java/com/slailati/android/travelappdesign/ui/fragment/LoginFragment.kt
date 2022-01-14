package com.slailati.android.travelappdesign.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.slailati.android.travelappdesign.databinding.FragmentLoginBinding
import com.slailati.android.travelappdesign.ui.customview.LoadingButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.run {
            setOnClickListener {
                fakeLoginProcess()
            }
        }
    }

    private fun LoadingButton.fakeLoginProcess() {
        initLoading()
        lifecycleScope.launch {
            delay(4_000)
            stopLoading()
        }
    }

}