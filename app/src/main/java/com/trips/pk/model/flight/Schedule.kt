package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("id")
    val id:Int,
    @SerializedName("frequency")
    val frequency:String,
    @SerializedName("stopCount")
    val stopCount:Int,
    @SerializedName("eTicketable")
    val eTicketable:Boolean,
    @SerializedName("totaldistence")
    val totalDistance:String,
    @SerializedName("elapsedTime")
    val elapsedTime:String,
    @SerializedName("departure")
    val departure: Departure,
    @SerializedName("arrival")
    val arrival: Arrival,
    @SerializedName("carrier")
    val carrier: Carrier
)
