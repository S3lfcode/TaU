package com.s3lfcode.tau.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
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
        binding.notes.setOnClickListener{
            (activity as MainActivity).navigateToFragment(NotesFragment.newInstance())
        }
        binding.linksSite.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://mrsu.ru/ru/university/faculty/fmiit/"))
            startActivity(browserIntent)
        }
        binding.linksVK.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/fmit.mrsu"))
            startActivity(browserIntent)
        }
    }
}