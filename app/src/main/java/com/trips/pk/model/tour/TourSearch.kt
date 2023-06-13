package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

data class TourSearch(
    @SerializedName("id")
    val id:Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("days")
    val days: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("minPrice")
    val minPrice: MinPrice?=null,
    @SerializedName("shortDiscription")
    val shortDiscription: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String
){

    val thumbnailLink:String get() = if (image.isNullOrEmpty() || image=="N/A") "" else "$ADMIN_BASE_URL$image"
}