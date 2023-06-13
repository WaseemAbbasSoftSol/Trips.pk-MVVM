package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class MinPrice(
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("price")
    val price: Int
)