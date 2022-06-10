package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
import com.s3lfcode.tau.databinding.FragmentChatbotBinding

class ChatBotFragment : Fragment(R.layout.fragment_chatbot) {
    companion object{
        fun newInstance() = ChatBotFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentChatbotBinding.bind(view)
        binding.menu.setOnClickListener{
            (activity as MainActivity).navigateToFragment(MenuFragment.newInstance())
        }
    }
}