package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("code")
    val code:String
){
    override fun toString(): String = name
}
