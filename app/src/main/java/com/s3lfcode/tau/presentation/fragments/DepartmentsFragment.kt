package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.R

class DepartmentsFragment : Fragment(R.layout.fragment_departments) {
    companion object{
        fun newInstance() = DepartmentsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}