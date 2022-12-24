package com.trips.pk.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("responseStatus")
    val responseStatus:Int,
    @SerializedName("isDone")
    val isDone:Boolean,
    @SerializedName("errors")
    val error:List<BaseError>,
    @SerializedName("data")
    val data:T
    )