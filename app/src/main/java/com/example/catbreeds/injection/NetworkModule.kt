package com.example.catbreeds.injection

import com.example.catbreeds.network.CatsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideCatsApiService(): CatsApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CatsApiService::class.java)
    }
}