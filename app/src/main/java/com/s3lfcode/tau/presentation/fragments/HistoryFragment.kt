package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.R

class HistoryFragment : Fragment(R.layout.fragment_history) {
    companion object{
        fun newInstance() = HistoryFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}