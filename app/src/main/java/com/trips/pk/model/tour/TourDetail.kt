package com.trips.pk.model.tour

import com.google.gson.annotations.SerializedName
import com.trips.pk.model.flight.Countries

data class TourDetail(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("tourPackagePrices")
    val priceDetails:List<TourPackagePrices>,
    @SerializedName("thumbnailImage")
    val thumbnail:String,
    @SerializedName("")
    val origin:String,
    @SerializedName("numberOfDays")
    val noOfDays:Int,
    @SerializedName("tourOrigion")
    val tourOrigin:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("essentialHeading")
    val essentialHeading:String,
    @SerializedName("essentialDetail")
    val essentialDetail:String,
    @SerializedName("isActive")
    val isActive:Boolean,
    @SerializedName("sortOrder")
    val sortOrder:Int,
    @SerializedName("country_ID")
    val countryId:Int,
    @SerializedName("tourStatus")
    val tourStatus:TourStatus,
    @SerializedName("countries")
    val country:Countries,
    @SerializedName("city_ID")
    val cityId:Int,
    @SerializedName("cities")
    val city:City,
    @SerializedName("tourItineraryDetails")
    val tourItineraryDetails: List<TourItineraryDetails>,
    ){
  //  val thumbnailLink:String get() = "adminapi.gotravel.pk$thumbnail"
    val thumbnailLink:String get() = "https://adminapi.gotravel.pk/$thumbnail"

    fun getTotalPrices():String{
        var tPrice = 0
        val allPrices= arrayListOf<Int>()
        try {
            for (item in priceDetails){
                allPrices.add(item.adultPrice+item.childPrice)
            }

            //Now get least price and return
            tPrice = (allPrices.minOrNull() ?: 0)

        }catch (e:Exception){
            e.printStackTrace()
        }
        val p = "Rs. $tPrice"
        return p
    }

    fun getDiscountedPrice():String{
        var dPrice = 0
        var tPrice = 0
        val discountedPrices= arrayListOf<Int>()
        val allPrices = arrayListOf<Int>()
        try {
            for (item in priceDetails){
                discountedPrices.add(item.adultDiscount+item.childDiscount)
                allPrices.add(item.adultPrice+item.childPrice)
            }

            //Now get least price and return
            dPrice = (discountedPrices.minOrNull() ?: 0)
            tPrice = (allPrices.minOrNull() ?: 0)

        }catch (e:Exception){
            e.printStackTrace()
        }
        val p = tPrice - dPrice
        return "Rs.$p"
    }

}