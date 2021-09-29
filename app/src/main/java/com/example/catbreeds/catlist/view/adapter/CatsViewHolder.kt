package com.example.catbreeds.catlist.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.R
import com.example.catbreeds.models.CatsBreed

class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val catName: TextView = itemView.findViewById(R.id.cat_name)
    private val catImage: TextView = itemView.findViewById(R.id.cat_image)

    fun bind(cat: CatsBreed) {
        catName.text = cat.name
    }
}