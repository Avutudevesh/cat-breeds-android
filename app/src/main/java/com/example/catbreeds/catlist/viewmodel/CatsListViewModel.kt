package com.example.catbreeds.catlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.catbreeds.models.CatsBreed

abstract class CatsListViewModel(): ViewModel() {

    sealed class Result {
        object Loading: Result()
        data class Success(val cats: List<CatsBreed>) : Result()
        object Error : Result()
    }

    abstract val stateLiveData: LiveData<Result>

    abstract fun fetchCatsList()

}