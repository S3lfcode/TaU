package com.s3lfcode.tau

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {
    companion object{
        fun newInstance() = MenuFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMenuBinding.bind(view)
        binding.infUniversity.setOnClickListener{
            (activity as MainActivity).navigateToFragment(UniversityFragment.newInstance())
        }
    }
}