package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class FareComponents(
    @SerializedName("id")
    val id:Int,
    @SerializedName("cabinCode")
    val cabinCode:String,
    @SerializedName("cabinName")
    val cabinName:String,
)
