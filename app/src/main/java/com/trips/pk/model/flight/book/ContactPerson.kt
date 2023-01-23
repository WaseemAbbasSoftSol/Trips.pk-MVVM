package com.trips.pk.model.flight.book

import com.google.gson.annotations.SerializedName

data class ContactPerson(
    @SerializedName("Name")
    val name:String="",
    @SerializedName("Number")
    val number:String="",
    @SerializedName("Gender")
    val gender:String="",
    @SerializedName("Email")
    val email:String="",
    @SerializedName("zip")
    val zipCode:String="",
    @SerializedName("Address")
    val address:String="",
    @SerializedName("country")
    val countryId:String="Pakistan",
    val countryName:String = "Pakistan",
    @SerializedName("city")
    val cityId:String="",
    val cityName :String = ""
)
