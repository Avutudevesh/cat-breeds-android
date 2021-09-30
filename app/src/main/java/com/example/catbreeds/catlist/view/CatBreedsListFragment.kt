package com.example.catbreeds.catlist.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.CatsApplication
import com.example.catbreeds.MainActivity
import com.example.catbreeds.R
import com.example.catbreeds.catdetails.CatBreedDetailsFragment
import com.example.catbreeds.catlist.view.adapter.CatsListAdapter
import com.example.catbreeds.catlist.view.pagination.Paginator
import com.example.catbreeds.catlist.view.pagination.RecyclerViewBasePaginator
import com.example.catbreeds.catlist.viewmodel.CatsListViewModel
import com.example.catbreeds.catlist.viewmodel.CatsListViewModelFactory
import com.example.catbreeds.catlist.viewmodel.CatsListViewModelImpl
import javax.inject.Inject

class CatBreedsListFragment : Fragment(R.layout.cat_list_fragment) {

    @Inject
    lateinit var adapter: CatsListAdapter

    @Inject
    lateinit var catsListViewModelFactory: CatsListViewModelFactory

    private val catsListViewModel: CatsListViewModel by lazy {
        ViewModelProvider(this, catsListViewModelFactory)[CatsListViewModelImpl::class.java]
    }

    @Inject
    lateinit var paginator: Paginator<RecyclerView>

    private lateinit var listView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
        adapter.onItemClickLiveData.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace(R.id.fragment_container_view, CatBreedDetailsFragment.newInstance(it))
            }
        }
    }

    private fun setUpRecyclerView() {
        listView = requireActivity().findViewById(R.id.cats_list)
        this.adapter = CatsListAdapter()
        listView.adapter = this.adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        paginator = RecyclerViewBasePaginator()
        paginator.bind(listView) {
            catsListViewModel.fetchCatsList()
        }
    }

    private fun setUpViewModel() {
        catsListViewModel.stateLiveData.observe(viewLifecycleOwner, ::onStateChanged)
        catsListViewModel.fetchCatsList()
    }

    private fun onStateChanged(state: CatsListViewModel.Result) {
        paginator.isLoading = false
        when (state) {
            is CatsListViewModel.Result.Success -> {
                paginator.hasMoreDataAvailable = true
                adapter.submitList(state.cats)
            }
            CatsListViewModel.Result.Error -> {
                Toast.makeText(requireContext(), "Sorry, Something went wrong!", Toast.LENGTH_LONG)
                    .show()
            }
            CatsListViewModel.Result.Loading -> {
                paginator.isLoading = true
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
            }
            CatsListViewModel.Result.PageLoading -> {
                paginator.isLoading = true
                Toast.makeText(requireContext(), "Page Loading...", Toast.LENGTH_LONG).show()
            }
            CatsListViewModel.Result.Empty -> {
                paginator.hasMoreDataAvailable = false
            }
        }
    }


}