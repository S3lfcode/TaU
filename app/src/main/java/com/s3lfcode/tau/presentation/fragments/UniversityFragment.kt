package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
import com.s3lfcode.tau.databinding.FragmentUniversityBinding

class UniversityFragment : Fragment(R.layout.fragment_university) {
    companion object{
        fun newInstance() = UniversityFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUniversityBinding.bind(view)
        binding.history.setOnClickListener{
            (activity as MainActivity).navigateToFragment(HistoryFragment.newInstance())
        }
        binding.leadership.setOnClickListener{
            (activity as MainActivity).navigateToFragment(LeadershipFragment.newInstance())
        }
        binding.departments.setOnClickListener{
            (activity as MainActivity).navigateToFragment(DepartmentsFragment.newInstance())
        }
    }
}