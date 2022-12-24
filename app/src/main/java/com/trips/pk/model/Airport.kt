package com.trips.pk.model

import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("id")
    val id:Int,
    @SerializedName("airPortName")
   val airportName:String,
    @SerializedName("airPortCode")
    val airportCode:String,
    @SerializedName("creation_Date")
    val creationDate:String,
    @SerializedName("updation_Date")
    val updationDate:String,
    @SerializedName("isActive")
   val isActive:Boolean,
    @SerializedName("isDelete")
    val isDelete:Boolean
)
