package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class TourPackagePrices(
    @SerializedName("adult_Discount")
    val adultDiscount: Int,
    @SerializedName("adult_Price")
    val adultPrice: Int,
    @SerializedName("child_Discount")
    val childDiscount: Int,
    @SerializedName("child_Price")
    val childPrice: Int,
    @SerializedName("currencies")
    val currencies: Any,
    @SerializedName("currency_ID")
    val currencyID: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("tourPackage_ID")
    val tourPackageID: Int,
    @SerializedName("tourPackages")
    val tourPackages: Any
)