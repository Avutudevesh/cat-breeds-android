package com.example.catbreeds.catlist.repository.injection

import com.example.catbreeds.catlist.repository.CatsListRepository
import com.example.catbreeds.catlist.repository.CatsListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CatsListRepositoryModule {

    @Binds
    abstract fun provideCatsRepository(repo: CatsListRepositoryImpl): CatsListRepository
}