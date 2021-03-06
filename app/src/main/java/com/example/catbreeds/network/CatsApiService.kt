package com.example.catbreeds.network

import com.example.catbreeds.models.CatsBreed
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApiService {

    @GET("/v1/breeds")
    suspend fun getCatsList(
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 60
    ): List<CatsBreed>

}