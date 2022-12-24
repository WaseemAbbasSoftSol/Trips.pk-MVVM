package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class Legs(
    @SerializedName("id")
    val id:Int,
    @SerializedName("elapsedTime")
    val elapsedTime:String,
    @SerializedName("stops")
    val stops:String,
    @SerializedName("schedules")
    val schedules:List<Schedule>,
    @SerializedName("baggageInformation")
    val beggageInformation:BaggageInformation
)
