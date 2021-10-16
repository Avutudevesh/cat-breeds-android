package com.example.catbreeds.catlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.catbreeds.models.CatsBreed

abstract class CatsListViewModel : ViewModel() {

    sealed class Result {
        object Loading : Result()
        data class Success(val cats: List<Breed>) : Result()
        object Empty : Result()
        object Error : Result()
        object PageLoading : Result()
    }

    abstract val stateLiveData: LiveData<Result>

    abstract fun fetchCatsList()

}