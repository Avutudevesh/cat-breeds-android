package com.example.catbreeds.catlist.repository

import com.example.catbreeds.models.CatsBreed

interface CatsListRepository {

    suspend fun fetchCatsList(): List<CatsBreed>
}