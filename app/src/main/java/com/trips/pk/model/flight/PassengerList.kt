package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class PassengerList(
@SerializedName("passengerInfo")
val passengerInfo:PassengerInfo
)
