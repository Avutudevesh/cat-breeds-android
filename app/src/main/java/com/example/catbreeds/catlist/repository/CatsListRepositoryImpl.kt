package com.example.catbreeds.catlist.repository

import com.example.catbreeds.models.CatsBreed

class CatsListRepositoryImpl: CatsListRepository {

    override suspend fun fetchCatsList(): List<CatsBreed> {
        //TODO
        return emptyList()
    }
}