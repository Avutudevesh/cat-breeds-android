package com.example.catbreeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.catbreeds.catlist.view.CatBreedsListFragment
import com.example.catbreeds.injection.AppComponent

class MainActivity : AppCompatActivity() {
    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as CatsApplication).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CatBreedsListFragment>(R.id.fragment_container_view)
            }
        }
    }
}