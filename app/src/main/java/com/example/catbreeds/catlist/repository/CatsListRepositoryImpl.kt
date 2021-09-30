package com.example.catbreeds.catlist.repository


import com.example.catbreeds.models.CatsBreed
import com.example.catbreeds.network.CatsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CatsListRepositoryImpl @Inject constructor(
    private var catsApiService: CatsApiService
) : CatsListRepository {

    override suspend fun fetchCatsList(page: Int): List<CatsBreed> {
        return withContext(Dispatchers.IO)
        {
            catsApiService.getCatsList(page)
        }
    }
}