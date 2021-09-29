package com.example.catbreeds.catlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.catbreeds.catlist.repository.CatsListRepository
import com.example.catbreeds.catlist.repository.CatsListRepositoryImpl
import kotlinx.coroutines.launch
import java.lang.Exception

class CatsListViewModelImpl : CatsListViewModel() {

    override val stateLiveData: MutableLiveData<Result> = MutableLiveData()

    private val catsRepository: CatsListRepository = CatsListRepositoryImpl()

    override fun fetchCatsList() {
        stateLiveData.value = Result.Loading
        viewModelScope.launch {
            try {
                val cats = catsRepository.fetchCatsList()
                stateLiveData.value = Result.Success(cats)
            } catch (error: Exception) {
                stateLiveData.value = Result.Error
            }
        }
    }


}