package com.trips.pk.model.flight.book


import com.google.gson.annotations.SerializedName

data class PassportInfo(
    @SerializedName("CountryID")
    val countryID: Int=0,
    @SerializedName("ExpirtyDate")
    val expirtyDate: String="",
    @SerializedName("PassportNumber")
    val passportNumber: String=""
)