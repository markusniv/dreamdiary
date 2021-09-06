package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamdiaryscratch.DreamDiary
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.RecyclerViewAdapter
import com.example.dreamdiaryscratch.dataclasses.*
import kotlinx.android.synthetic.main.fragment_diary.view.*
import kotlin.random.Random

class DiaryFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diary, container, false)

        val adapter = RecyclerViewAdapter()
        val recyclerView = view.rv_diary
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        mEntryViewModel.readAllData.observe(viewLifecycleOwner, Observer { entry ->
            adapter.setData(entry)
        })

        return view
    }


/*    private fun createDummyEntries(size : Int) {
        for (i in 0..size) {
            val j = (1..3).random()
            val icon = when (j) {
                1 -> R.drawable.ic_mood_sad
                2 -> R.drawable.ic_mood_veryhappy
                else -> R.drawable.ic_mood_happy
            }
            val dreamType = when (j) {
                1 -> DreamType.NIGHTMARE
                2 -> DreamType.LUCID_DREAM
                else -> DreamType.REGULAR
            }
            val entry = Entry(i, dreamType,"dream_name$i","dream content",icon)
            EntrySingleton.entryList.add(entry)
        }
    }*/
}