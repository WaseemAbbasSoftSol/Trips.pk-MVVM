package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class PassengerInfo(
    @SerializedName("passengerType")
    val passengerType: String,
    @SerializedName("passengerNumber")
    val noOfPassenger: Int,
    @SerializedName("nonRefundable")
    val isRefundable: Boolean,
    @SerializedName("passengerTotalFare")
    val passengerTotalFare: PassengerTotalFare,
    @SerializedName("fareComponents")
    val fareComponent: List<FareComponents>,
    @SerializedName("baggageInformation")
    val beggageInformation: List<BaggageInformation>
)
