package com.s3lfcode.tau.presentation.fragments

import android.os.Bundle
import android.widget.ArrayAdapter
import android.util.SparseBooleanArray
import android.view.View
import androidx.fragment.app.Fragment
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
import com.s3lfcode.tau.databinding.FragmentNotesBinding
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(R.layout.fragment_notes) {
    companion object{
        fun newInstance() = NotesFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemlist = arrayListOf<String>()
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_multiple_choice, itemlist)

        // добавить заметку +
        add.setOnClickListener{
            itemlist.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        // очистить
        clear.setOnClickListener{
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        // удалить
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                    item--
                }
            }

            position.clear()
            adapter.notifyDataSetChanged()
        }

        val binding = FragmentNotesBinding.bind(view)

        binding.menu.setOnClickListener{
            (activity as MainActivity).navigateToFragment(MenuFragment.newInstance())
        }
    }
}