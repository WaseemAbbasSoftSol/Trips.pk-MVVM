package com.trips.pk.model.flight


import com.google.gson.annotations.SerializedName

data class Arrival(
    @SerializedName("airport")
    val airport: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("dateAdjustment")
    val dateAdjustment: String,
    @SerializedName("terminal")
    val terminal: String,
    @SerializedName("time")
    val time: String
)