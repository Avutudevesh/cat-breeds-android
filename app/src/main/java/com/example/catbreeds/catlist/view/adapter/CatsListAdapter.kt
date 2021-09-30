package com.example.catbreeds.catlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.R
import com.example.catbreeds.models.CatsBreed
import javax.inject.Inject

class CatsListAdapter @Inject constructor() : ListAdapter<CatsBreed, CatsViewHolder>(diffCallback) {

    private val _onItemClickLiveData: MutableLiveData<CatsBreed> = MutableLiveData()

    val onItemClickLiveData: LiveData<CatsBreed>
        get() = _onItemClickLiveData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_breed, parent, false)
        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position), _onItemClickLiveData)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CatsBreed>() {

            override fun areItemsTheSame(oldItem: CatsBreed, newItem: CatsBreed): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CatsBreed,
                newItem: CatsBreed
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}