package com.example.dreamdiaryscratch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamdiaryscratch.dataclasses.Entry

class RecyclerViewAdapter(private val dreamList: List<Entry>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.diary_entry,
            parent, false
        )
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = dreamList[position]

        holder.dreamIcon.setImageResource(currentItem.imageResource)
        holder.dreamName.text = currentItem.dreamTitle
        holder.dreamType.text = currentItem.dreamTypeToString
    }

    override fun getItemCount(): Int = dreamList.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dreamIcon: ImageView = itemView.findViewById(R.id.dream_icon)
        val dreamName: TextView = itemView.findViewById(R.id.dream_name)
        val dreamType: TextView = itemView.findViewById(R.id.dream_type)
    }
}