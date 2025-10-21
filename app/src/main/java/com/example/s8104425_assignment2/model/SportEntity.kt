package com.example.s8104425_assignment2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json

//parcelable allows passing this object between activities using Intent
@Parcelize
data class SportEntity(
    @Json(name = "name") val sportName: String, //Map JSON field"name" to sportName
    @Json(name = "count") val playerCount: Int, //Map JSON field "count"
    @Json(name = "type") val fieldType: String, //Map JSON field "type"
    @Json(name = "Olympic") val olympicSport: Boolean, //this is Olympic sport or not
    @Json(name = "Description") val description: String //Description of sport
) : Parcelable