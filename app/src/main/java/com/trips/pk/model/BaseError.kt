package com.trips.pk.model

import com.google.gson.annotations.SerializedName

data class BaseError(
    @SerializedName("code")
    val code:Int,
    @SerializedName("description")
    val errorDescription:String,
)
