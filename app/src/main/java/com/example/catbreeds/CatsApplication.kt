package com.example.catbreeds

import android.app.Application
import com.example.catbreeds.injection.DaggerAppComponent

class CatsApplication: Application() {
    val appComponent = DaggerAppComponent.create()
}