package com.trips.pk.model.visa


import com.google.gson.annotations.SerializedName

data class VisaTypes(
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("visas")
    val visas: List<Any>
)