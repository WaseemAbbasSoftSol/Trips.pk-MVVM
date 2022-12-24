package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItinerariesDetail(
    @SerializedName("id")
    val  id:Int,
    @SerializedName("pricingSource")
    val pricingSource:String,
    @SerializedName("pricingInformation")
    val pricingInformation:PricingInformation,
    @SerializedName("diversitySwapper")
    val diversitySwapper:String,
    @SerializedName("legs")
    val legs:List<Legs>,
): Serializable
