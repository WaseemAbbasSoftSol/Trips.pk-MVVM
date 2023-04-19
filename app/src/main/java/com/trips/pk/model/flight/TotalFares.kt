package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class TotalFares(
    @SerializedName("totalPrice")
    val price:Double,
    @SerializedName("totalTaxAmount")
    val tax:Double,
    @SerializedName("farePlusTax")
    val totalFare:Double,
    @SerializedName("currency")
    val currency:String,
)
