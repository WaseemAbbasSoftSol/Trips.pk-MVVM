package com.trips.pk.model.visa

import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

data class VisaCountries(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,

    @SerializedName("code")
    val code:String,
    @SerializedName("flag")
    val flag:String = "",
    @SerializedName("logo")
    val logo:String,
    @SerializedName("promotedforVisa")
    val isPromoted:Boolean = false,
    @SerializedName("shortDescription")
    val description:String = "",
    @SerializedName("price")
    val price:String = "",

){
    override fun toString(): String = name

    val countryFlag:String get() =  if (flag.isNullOrEmpty() || flag=="N/A") "" else "$ADMIN_BASE_URL$flag"
}
