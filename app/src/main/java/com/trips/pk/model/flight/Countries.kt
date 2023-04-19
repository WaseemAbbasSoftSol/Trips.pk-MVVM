package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

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

    val countryFlag:String get() =  if (flag.isNullOrEmpty() || flag=="N/A") "" else "$ADMIN_BASE_URL$flag"
}
