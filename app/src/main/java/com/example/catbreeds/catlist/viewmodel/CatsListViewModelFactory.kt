package com.example.catbreeds.catlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catbreeds.catlist.repository.CatsListRepository
import javax.inject.Inject

class CatsListViewModelFactory @Inject constructor(
    private val catsRepository: CatsListRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatsListViewModelImpl(MutableLiveData(), catsRepository) as T
    }
}