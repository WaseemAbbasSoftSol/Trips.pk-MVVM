package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class PricingInformation(

    @SerializedName("fare")
    val fare: Fare
) {
}