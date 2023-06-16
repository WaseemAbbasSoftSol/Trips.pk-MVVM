package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class CountriesWithCities(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("region_ID")
    val regionID: Int,
    @SerializedName("cities")
    val cities: List<City>,
    val flag : String = ""
//    @SerializedName("airPorts")
//    val airPorts: Any,
//    @SerializedName("bookingDetails")
//    val bookingDetails: Any,
//    @SerializedName("passportDetails")
//    val passportDetails: Any,
//    @SerializedName("region")
//    val region: Any,
//    @SerializedName("tourItineraryDetails")
//    val tourItineraryDetails: Any,
//    @SerializedName("tourPackages")
//    val tourPackages: Any,
//    @SerializedName("visas")
//    val visas: Any
)