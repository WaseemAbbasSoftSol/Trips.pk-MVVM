package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class PassengerTotalFare(
    @SerializedName("farePlusTax")
    val fare:Double,
    @SerializedName("totalFare")
    val totalFare:Double,
    @SerializedName("totalTaxAmount")
    val taxAmount:Double,
    @SerializedName("currency")
    val currency:String,
)
