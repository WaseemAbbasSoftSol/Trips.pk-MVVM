package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class PricingInformation(
    @SerializedName("totalFare")
  val fare:Double,
    @SerializedName("totalTaxAmount")
    val tax:Double,
    @SerializedName("farePlusTax")
    val totalFare:Double,
    @SerializedName("currency")
    val currency:String
) {
}