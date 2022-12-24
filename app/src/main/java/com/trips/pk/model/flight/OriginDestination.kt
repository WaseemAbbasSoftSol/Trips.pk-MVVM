package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class OriginDestination(
    @SerializedName("departureDate")
    val departureDate: String,
    @SerializedName("departureLocation")
    val departureLocation: String,
    @SerializedName("arrivalLocation")
    val arrivalLocation: String,
)