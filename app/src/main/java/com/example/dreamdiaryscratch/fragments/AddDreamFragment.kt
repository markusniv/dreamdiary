package com.example.dreamdiaryscratch.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dreamdiaryscratch.MainActivity
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.functions.inputCheck
import com.example.dreamdiaryscratch.functions.resetMoods
import com.example.dreamdiaryscratch.model.Entry
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add_dream.*
import kotlinx.android.synthetic.main.fragment_add_dream.view.*

class AddDreamFragment : Fragment() {


    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_dream, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        val res = resources

        val defaultMood = view.diary_add_mood_happy
        defaultMood.isChecked = true
        defaultMood.background.setTint(resources.getColor(R.color.darker_night_blue))

        view.diary_add_addbutton.setOnClickListener {
            resetMoods(view.diary_add_mood_happy, res)
            resetMoods(view.diary_add_mood_sad, res)
            resetMoods(view.diary_add_mood_veryhappy, res)
            insertEntryToDatabase()
            findNavController().navigate(R.id.action_addDreamFragment_to_diaryFragment)
        }
        view.diary_add_radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            resetMoods(view.diary_add_mood_happy, res)
            resetMoods(view.diary_add_mood_sad, res)
            resetMoods(view.diary_add_mood_veryhappy, res)

            val checked = view.findViewById<RadioButton>(i)
            checked.background.setTint(resources.getColor(R.color.darker_night_blue))
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

}