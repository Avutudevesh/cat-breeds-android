package com.example.catbreeds.injection

import androidx.recyclerview.widget.RecyclerView
import com.example.catbreeds.catlist.view.pagination.Paginator
import com.example.catbreeds.catlist.view.pagination.RecyclerViewBasePaginator
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    abstract fun providePaginator(paginator: RecyclerViewBasePaginator): Paginator<RecyclerView>

}