package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.functions.inputCheck
import com.example.dreamdiaryscratch.functions.resetMoods
import com.example.dreamdiaryscratch.model.Entry
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add_dream.*
import kotlinx.android.synthetic.main.fragment_add_dream.diary_add_title_edit
import kotlinx.android.synthetic.main.fragment_add_dream.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.security.KeyStore

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mEntryViewModel : EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        val res = resources

        resetMoods(view.diary_edit_mood_happy, res)
        resetMoods(view.diary_edit_mood_sad, res)
        resetMoods(view.diary_edit_mood_veryhappy, res)

        view.diary_edit_title_edit.setText(args.currentEntry.dreamTitle)
        view.diary_edit_content_edit.setText(args.currentEntry.dreamContent)
        val checked = when(args.currentEntry.imageResource) {
            R.drawable.ic_mood_veryhappy -> view.diary_edit_mood_veryhappy.id
            R.drawable.ic_mood_happy -> view.diary_edit_mood_happy.id
            else -> view.diary_edit_mood_sad.id
        }
        val checkedBtn = view.findViewById<RadioButton>(checked)
        checkedBtn.isChecked = true
        checkedBtn.background.setTint(resources.getColor(R.color.dark_night_Blue))

        view.diary_edit_updatebutton.setOnClickListener {
            resetMoods(view.diary_edit_mood_happy, res)
            resetMoods(view.diary_edit_mood_sad, res)
            resetMoods(view.diary_edit_mood_veryhappy, res)
            updateEntry()
        }

        view.diary_edit_radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            resetMoods(view.diary_edit_mood_happy, res)
            resetMoods(view.diary_edit_mood_sad, res)
            resetMoods(view.diary_edit_mood_veryhappy, res)

            val checked = view.findViewById<RadioButton>(i)
            checked.background.setTint(resources.getColor(R.color.dark_night_Blue))
        }

        return view

    }

    private fun updateEntry() {
        val dreamTitle = diary_edit_title_edit.text.toString()
        val dreamMood : Int = when(diary_edit_radiogroup.checkedRadioButtonId) {
            R.id.diary_edit_mood_happy -> R.drawable.ic_mood_happy
            R.id.diary_edit_mood_sad -> R.drawable.ic_mood_sad
            else -> R.drawable.ic_mood_veryhappy
        }
        val dreamContent = diary_edit_content_edit.text.toString()

        if (inputCheck(dreamTitle, dreamContent)) {
            val updatedEntry = Entry(args.currentEntry.id, dreamTitle, dreamContent, dreamMood)
            mEntryViewModel.updateEntry(updatedEntry)
            Toast.makeText(requireContext(), "Entry updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_diaryFragment)
        }
    }
}