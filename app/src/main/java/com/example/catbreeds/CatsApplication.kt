package com.example.catbreeds

import android.app.Application
import com.example.catbreeds.injection.AppComponent
import com.example.catbreeds.injection.DaggerAppComponent

class CatsApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}