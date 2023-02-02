package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName

data class VehicleCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("logoURL")
    val logoURL: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("vehiclesModels")
    val vehiclesModels: List<VehiclesModel>
):java.io.Serializable{

    val fullImage:String get() = "https://adminapi.gotravel.pk/$logoURL"

    fun getLeastPrice():String{
        var p = 0
        try {
            for (item in vehiclesModels){
                for (sub in item.vehicles){
                    for (dirtyFlag in sub.vehiclesPrices){
                        if (p==0){
                            p = dirtyFlag.price
                        }else if (dirtyFlag.price<p){
                            p = dirtyFlag.price
                        }
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return "$p"
    }
}