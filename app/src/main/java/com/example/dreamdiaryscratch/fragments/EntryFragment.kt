package com.example.dreamdiaryscratch.fragments

import android.app.ActionBar
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_entry.view.*

class EntryFragment : Fragment() {

    private val args by navArgs<EntryFragmentArgs>()
    private lateinit var mEntryViewModel : EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        view.entry_view_title.text = args.currentEntry.dreamTitle
        view.entry_view_date.text = args.currentEntry.entryDate
        view.entry_view_content.text = args.currentEntry.dreamContent
        view.entry_view_mood.setBackgroundResource(args.currentEntry.imageResource)

        // Add menu
        setHasOptionsMenu(true)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_entryFragment_to_diaryFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.edit_menu) {
            editEntry()
        }
        if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editEntry() {
        val action = EntryFragmentDirections.actionEntryFragmentToUpdateFragment(args.currentEntry)
        findNavController().navigate(action)
    }

}