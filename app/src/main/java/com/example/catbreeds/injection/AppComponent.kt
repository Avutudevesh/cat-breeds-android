package com.example.catbreeds.injection

import com.example.catbreeds.MainActivity
import com.example.catbreeds.catlist.repository.injection.CatsListRepositoryModule
import com.example.catbreeds.catlist.view.CatBreedsListFragment
import dagger.Component

@Component(modules = [NetworkModule::class, CatsListRepositoryModule::class, MainModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(catsListFragment: CatBreedsListFragment)


}