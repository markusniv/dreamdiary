package com.example.dreamdiaryscratch.fragments

import android.app.AlertDialog
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        mEntryViewModel.readAllData.observe(viewLifecycleOwner, { entry ->
            if (entry.isEmpty()) {
                view.delete_all_button_name.setTextColor(resources.getColor(R.color.light_grey))
                view.delete_all_button_card.setOnClickListener {
                    Toast.makeText(requireContext(),"Diary already empty!", Toast.LENGTH_SHORT).show()
                }
            } else {
                view.delete_all_button_name.setTextColor(resources.getColor(R.color.dark_grey))
                view.delete_all_button_card.setOnClickListener {
                    deleteAllUsers()
                }
            }
        })



        return view
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mEntryViewModel.deleteAllEntries()
            Toast.makeText(requireContext(),
                "Succesfully emptied the diary.",
                Toast.LENGTH_LONG).show()
            setHasOptionsMenu(false)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Really delete all diary entries?")
        builder.setMessage("Are you sure you want to empty your entire diary? This action is permanent!")
        builder.create().show()
    }
}