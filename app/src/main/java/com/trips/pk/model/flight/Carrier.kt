package com.trips.pk.model.flight


import com.google.gson.annotations.SerializedName

data class Carrier(
    @SerializedName("alliances")
    val alliances: String,
    @SerializedName("codeShared")
    val codeShared: String,
    @SerializedName("disclosure")
    val disclosure: String,
    @SerializedName("equipment")
    val equipment: String,
    @SerializedName("marketing")
    val marketing: String,
    @SerializedName("marketingFlightNumber")
    val marketingFlightNumber: Int,
    @SerializedName("operating")
    val operating: String,
    @SerializedName("operatingFlightNumber")
    val operatingFlightNumber: Int
)