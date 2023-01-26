package com.trips.pk.model.visa


import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("sign")
    val sign: String,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("tourPackagePrices")
    val tourPackagePrices: Any,
    @SerializedName("visas")
    val visas: List<Any>
)