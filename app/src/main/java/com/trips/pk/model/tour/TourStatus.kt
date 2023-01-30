package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class TourStatus(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortOrder")
    val sortOrder: Int
)