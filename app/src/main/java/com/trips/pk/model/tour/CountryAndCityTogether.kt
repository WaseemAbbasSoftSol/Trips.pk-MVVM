package com.trips.pk.model.tour

data class CountryAndCityTogether(
   val id :Int =0,
    val name : String = "",
 val code : String = ""
){
    override fun toString(): String = name
}
