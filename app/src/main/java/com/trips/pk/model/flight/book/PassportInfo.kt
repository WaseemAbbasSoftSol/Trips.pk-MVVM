package com.trips.pk.model.flight.book


import com.google.gson.annotations.SerializedName

data class PassportInfo(
    @SerializedName("CountryID")
    val countryID: Int=0,
    val countryName:String="Pakistan",
    @SerializedName("ExpirtyDate")
    val expirtyDate: String="",
    @SerializedName("PassportNumber")
    val passportNumber: String=""
)