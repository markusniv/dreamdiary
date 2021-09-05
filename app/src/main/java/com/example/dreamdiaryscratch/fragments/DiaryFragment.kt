package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamdiaryscratch.DreamDiary
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.RecyclerViewAdapter
import com.example.dreamdiaryscratch.dataclasses.Addition
import com.example.dreamdiaryscratch.dataclasses.AdditionSingleton
import com.example.dreamdiaryscratch.dataclasses.Dream
import com.example.dreamdiaryscratch.dataclasses.DreamType
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiaryFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //createDummyEntries(50)

        val recyclerView : RecyclerView = requireView().findViewById(R.id.rv_diary)
        recyclerView.adapter = RecyclerViewAdapter(AdditionSingleton.additionList)
        recyclerView.layoutManager = LinearLayoutManager(DreamDiary.ctx)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun createDummyEntries(size : Int) {
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
            val dream = Dream(dreamType,"dream_name$i")
            val addition = Addition(dream, icon)
            AdditionSingleton.additionList.add(addition)
        }
    }
}