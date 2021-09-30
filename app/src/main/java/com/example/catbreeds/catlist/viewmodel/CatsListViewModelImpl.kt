package com.example.catbreeds.catlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.catbreeds.catlist.repository.CatsListRepository
import com.example.catbreeds.catlist.repository.CatsListRepositoryImpl
import com.example.catbreeds.models.CatsBreed
import kotlinx.coroutines.launch
import java.lang.Exception

class CatsListViewModelImpl(
    override val stateLiveData: MutableLiveData<Result>,
    private val catsRepository: CatsListRepository
) : CatsListViewModel() {

    private var items = mutableListOf<CatsBreed>()

    private var page = 1

    override fun fetchCatsList() {
        if (page == 1) {
            stateLiveData.value = Result.Loading
        } else {
            stateLiveData.value = Result.PageLoading
        }
        viewModelScope.launch {
            try {
                val cats = catsRepository.fetchCatsList(page)
                if (cats.isEmpty()) {
                    stateLiveData.value = Result.Empty
                } else {
                    items.addAll(cats)
                    page += 1
                    stateLiveData.value = Result.Success(items)
                }
            } catch (error: Exception) {
                stateLiveData.value = Result.Error
            }
        }
    }

}