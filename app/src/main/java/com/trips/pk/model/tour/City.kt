package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class City(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("country_ID")
    val countryID: Int,
//    @SerializedName("countries")
//    val countries: Any,
//    @SerializedName("bookingDetails")
//    val bookingDetails: Any,
//    @SerializedName("tourItineraryDetails")
//    val tourItineraryDetails: Any,
//    @SerializedName("tourPackages")
//    val tourPackages: Any
)