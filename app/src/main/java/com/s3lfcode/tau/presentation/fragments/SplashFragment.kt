package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
import com.s3lfcode.tau.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    companion object{
        fun newInstance() = SplashFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSplashBinding.bind(view)
        binding.startButton.setOnClickListener{
            (activity as MainActivity).navigateToFragment(ChatWithBotFragment.newInstance())
        }
    }
}