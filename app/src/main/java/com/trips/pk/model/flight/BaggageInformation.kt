package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class BaggageInformation(
    @SerializedName("airlineCode")
    val airlineCode:String,
    @SerializedName("allowance")
    val allowance:String,
    @SerializedName("provisionType")
    val provisionType:String,
    @SerializedName("airlineLogo")
    val airlineLogo:String
)
