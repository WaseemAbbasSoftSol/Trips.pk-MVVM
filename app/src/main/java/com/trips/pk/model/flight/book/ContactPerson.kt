package com.trips.pk.model.flight.book

import com.google.gson.annotations.SerializedName

data class ContactPerson(
    @SerializedName("Name")
    val name:String="",
    @SerializedName("Number")
    val number:String="",
    @SerializedName("Gender")
    val gender:Boolean = true,
    @SerializedName("Email")
    val email:String="",
    @SerializedName("zip")
    val zipCode:String="",
    @SerializedName("Address")
    val address:String="",
    @SerializedName("country")
    val countryId:Int=157,
    val countryName:String = "Pakistan",
    @SerializedName("city")
    val cityId:Int=2836,
    val cityName :String = ""
)
