package com.example.dreamdiaryscratch.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dreamdiaryscratch.R
import com.example.dreamdiaryscratch.RecyclerViewAdapter
import com.example.dreamdiaryscratch.viewmodel.EntryViewModel
import kotlinx.android.synthetic.main.fragment_diary.view.*

class DiaryFragment : Fragment() {

    private lateinit var mEntryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diary, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_diaryFragment_to_addDreamFragment)
        }

        val adapter = RecyclerViewAdapter()
        val recyclerView = view.rv_diary
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mEntryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        mEntryViewModel.readAllData.observe(viewLifecycleOwner, Observer { entry ->
            adapter.setData(entry)
            if (entry.isNotEmpty()) setHasOptionsMenu(true)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
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