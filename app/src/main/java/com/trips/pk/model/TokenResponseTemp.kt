package com.trips.pk.model


import com.google.gson.annotations.SerializedName

data class TokenResponseTemp(
    @SerializedName("data")
    val data: String,
    @SerializedName("errors")
    val errors: List<BaseError>,
    @SerializedName("isDone")
    val isDone: Boolean,
    @SerializedName("responseStatus")
    val responseStatus: Int
)