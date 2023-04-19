package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName
import com.trips.pk.utils.changeStringDateFormat
import com.trips.pk.utils.splitTimeAndDate
import java.text.SimpleDateFormat

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
){

    fun getStartAndEndDate():String{
        val sDate= changeDateFormat(splitTimeAndDate(startDate))
        val eDate= changeDateFormat(splitTimeAndDate(endDate))
        return  "$sDate | $eDate"
    }

    val adultTotalPrice:String get() = "Rs. $adultPrice"
    val childTotalPrice:String get() = "Rs. $childPrice"

   private fun changeDateFormat(date:kotlin.String):kotlin.String{
        var formattedDate=""
        try {
            val fromUser = SimpleDateFormat("yyyy-MM-dd")
            val myFormat = SimpleDateFormat("dd-MM-yyyy")
            formattedDate =  myFormat.format(fromUser.parse(date))
        }catch (e:Exception){
            e.printStackTrace()
        }
        return formattedDate
    }

    fun getAdultDiscounts() : String{
        var p = 0
        try {
          p = adultPrice-adultDiscount
        }catch (e:Exception){
            e.printStackTrace()
        }
        return "Rs.$p"
    }

    fun getChildDiscounts() : String{
        var p = 0
        try {
            p = childPrice-childDiscount
        }catch (e:Exception){
            e.printStackTrace()
        }
        return "Rs.$p"
    }
}