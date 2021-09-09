package com.example.dreamdiaryscratch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamdiaryscratch.fragments.DiaryFragment
import com.example.dreamdiaryscratch.fragments.DiaryFragmentDirections
import com.example.dreamdiaryscratch.model.Entry
import kotlinx.android.synthetic.main.diary_entry.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private var entryList = emptyList<Entry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.diary_entry,
            parent,
            false
        )
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = entryList[position]

        holder.dreamIcon.setImageResource(currentItem.imageResource)
        holder.dreamName.text = currentItem.dreamTitle
        val dreamContentInList : List<String> = currentItem.dreamContent.split(" ")
        var words = ""
        var len = 6
        var dots = "..."
        if (len !in dreamContentInList.indices) {
            len = dreamContentInList.size - 1
            dots = ""
        }
        for (i in 0..len) words += "${dreamContentInList[i]} "
        words += dots
        holder.dreamContent.text = words
        holder.itemView.entryLayout.setOnClickListener {
            val action = DiaryFragmentDirections.actionDiaryFragmentToEntryFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(entry: List<Entry>) {
        this.entryList = entry
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = entryList.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dreamIcon: ImageView = itemView.findViewById(R.id.dream_icon)
        val dreamName: TextView = itemView.findViewById(R.id.dream_name)
        val dreamContent: TextView = itemView.findViewById(R.id.dream_content_short)
    }
}