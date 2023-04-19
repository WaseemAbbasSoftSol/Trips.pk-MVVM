package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class Fare(
    @SerializedName("totalFare")
    val fares:TotalFares,
    @SerializedName("passengerInfoList")
    val passengerList: List<PassengerList>
)
