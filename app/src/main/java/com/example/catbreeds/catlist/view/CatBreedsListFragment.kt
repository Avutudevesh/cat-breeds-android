package com.example.catbreeds.catlist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.R
import com.example.catbreeds.catlist.view.adapter.CatsListAdapter

class CatBreedsListFragment: Fragment(R.layout.cat_list_fragment) {

    private lateinit var listView : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        listView = requireActivity().findViewById(R.id.cats_list)
        listView.adapter = CatsListAdapter()
        listView.layoutManager = LinearLayoutManager(requireContext())
    }


}