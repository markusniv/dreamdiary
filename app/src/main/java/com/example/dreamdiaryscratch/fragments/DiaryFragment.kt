package com.example.dreamdiaryscratch.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.dreamdiaryscratch.DreamDiary
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.dataclasses.Addition
import com.example.dreamdiaryscratch.dataclasses.AdditionSingleton
import com.example.dreamdiaryscratch.dataclasses.Dream

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

    private lateinit var listView: ListView

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
        listView = requireView().findViewById(R.id.dreamlist)
        AdditionSingleton.additionList.add(Addition(Dream()))
        val dreamList = AdditionSingleton.additionList
        val parsedList = arrayOfNulls<String>(dreamList.size)
        for (i in 0 until dreamList.size) {
            val dream = dreamList[i].dream
            parsedList[i] = dream.content
        }
        val adapter = ArrayAdapter(DreamDiary.ctx!!, android.R.layout.simple_list_item_1, parsedList)
        listView.adapter = adapter

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
}