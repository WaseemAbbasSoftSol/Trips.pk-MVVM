package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName

data class VehiclesPrice(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("vehicle_ID")
    val vehicleID: Int,
    @SerializedName("vehicles")
    val vehicles: Any,
    @SerializedName("withDriver")
    val withDriver: Boolean,
    @SerializedName("zone")
    val zone: String
)