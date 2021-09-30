package com.example.catbreeds.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(url: String, context: Context) =
    Glide.with(context).load(url).into(this)
