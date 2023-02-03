package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

data class VehiclesModel(
    @SerializedName("category_ID")
    val categoryID: Int,
    @SerializedName("company_ID")
    val companyID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("isPromoted")
    val isPromoted: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("vehicles")
    val vehicles: List<Vehicle>,
    @SerializedName("vehiclesCategories")
    val vehiclesCategories: Any,
    @SerializedName("vehiclesCompanies")
    val vehiclesCompanies: Any
):java.io.Serializable{

    val carImage : String get() =  if (image.isNullOrEmpty() || image=="N/A") "" else "$ADMIN_BASE_URL$image"

    fun getLeastPrice(which:Int):String{
        var p = 0
        var duration = ""
        try {
            for (item in vehicles){
                for (sub in item.vehiclesPrices){
                    if (p==0){
                        p=sub.price
                        duration=sub.duration
                    }
                    else if (sub.price<p){
                        p=sub.price
                        duration=sub.duration
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return when(which){
            1 ->  "Rs.$p"
            else -> duration
        }

    }
}