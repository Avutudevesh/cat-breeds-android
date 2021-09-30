package com.example.catbreeds.catlist.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catbreeds.R
import com.example.catbreeds.models.CatsBreed
import com.example.catbreeds.utils.loadImage

class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val catName: TextView = itemView.findViewById(R.id.cat_name)
    private val catImage: ImageView = itemView.findViewById(R.id.cat_image)
    private val altName: TextView = itemView.findViewById(R.id.alternate_names)
    private val avgWeight: TextView = itemView.findViewById(R.id.cat_weight)

    fun bind(cat: CatsBreed?, clickLiveData: MutableLiveData<CatsBreed>) {
        cat?.let {
            catName.text = cat.name
            catImage.loadImage(cat.image?.url.orEmpty(), itemView.context)
            cat.alt_names?.let {
                altName.text = itemView.context.getString(R.string.alternate_names, it)
            } ?: kotlin.run {
                altName.visibility = View.GONE
            }
            cat.weight.metric?.let {
                avgWeight.text = itemView.context.getString(R.string.weight, it)
            } ?: kotlin.run {
                avgWeight.visibility = View.GONE
            }
            itemView.setOnClickListener {
                clickLiveData.value = cat
            }
        }
    }
}