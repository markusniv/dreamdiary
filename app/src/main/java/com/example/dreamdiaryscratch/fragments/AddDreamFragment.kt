package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.dataclasses.*
import org.w3c.dom.Text

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dreamTitle : EditText = requireView().findViewById(R.id.diary_add_title_edit)
        val dreamMood : RadioGroup = requireView().findViewById(R.id.diary_add_radiogroup)
        val dreamContent : EditText = requireView().findViewById(R.id.diary_add_content_edit)

        val dreamAddButton : Button = requireView().findViewById(R.id.diary_add_addbutton)

        dreamAddButton.setOnClickListener {
            val dreamTitleString = dreamTitle.text.toString()
            val dreamContentString = dreamContent.text.toString()
            val dreamMoodIcon : Int = when(dreamMood.checkedRadioButtonId) {
                R.id.diary_add_mood_happy -> R.drawable.ic_mood_happy
                R.id.diary_add_mood_sad -> R.drawable.ic_mood_sad
                else -> R.drawable.ic_mood_veryhappy
            }

            val newEntry = Entry(1,DreamType.REGULAR,dreamTitleString,dreamContentString,dreamMoodIcon)
            EntrySingleton.entryList.add(newEntry)
        }

    }
}