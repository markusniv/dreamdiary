package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dreamdiaryscratch.MainActivity
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.model.Entry
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add_dream.*
import kotlinx.android.synthetic.main.fragment_add_dream.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddDreamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_dream, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        view.diary_add_addbutton.setOnClickListener {
            insertEntryToDatabase()

            findNavController().navigate(R.id.action_addDreamFragment_to_diaryFragment)
        }

        return view
    }

    private fun insertEntryToDatabase() {
        val dreamTitle = diary_add_title_edit.text.toString()
        val dreamMood : Int = when(diary_add_radiogroup.checkedRadioButtonId) {
            R.id.diary_add_mood_happy -> R.drawable.ic_mood_happy
            R.id.diary_add_mood_sad -> R.drawable.ic_mood_sad
            else -> R.drawable.ic_mood_veryhappy
        }
        val dreamContent = diary_add_content_edit.text.toString()

        if (inputCheck(dreamTitle, dreamContent)) {
            val entry = Entry(0, dreamTitle, dreamContent, dreamMood)
            mEntryViewModel.addEntry(entry)
            Toast.makeText(requireContext(), "Succesfully added!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(dreamTitle : String,
                           dreamContent : String) : Boolean {
        return !(TextUtils.isEmpty(dreamTitle) && TextUtils.isEmpty(dreamContent))
    }

}