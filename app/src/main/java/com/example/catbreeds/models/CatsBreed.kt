package com.example.catbreeds.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatsBreed(
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: CatImage?,
    @Json(name = "name")
    val name: String,
    @Json(name = "life_span")
    val life_span: String?,
    @Json(name = "origin")
    val origin: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "wikipedia_url")
    val wikipedia_url: String?,
    @Json(name = "temperament")
    val temperament: String?,
    @Json(name = "weight")
    val weight: CatWeight,
    @Json(name = "alt_names")
    val alt_names: String?
) : Parcelable

@Parcelize
data class CatImage(
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val url: String?
) : Parcelable

@Parcelize
data class CatWeight(
    @Json(name = "metric")
    val metric: String?
) : Parcelable