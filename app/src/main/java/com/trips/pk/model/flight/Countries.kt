package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("code")
    val code:String,
    @SerializedName("flag")
    val flag:String = "",
    @SerializedName("promoted")
    val isPromoted:Boolean = false
){
    override fun toString(): String = name
}
