package com.trips.pk.data

import android.app.Application
import android.content.Context
import android.icu.text.ListFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trips.pk.R
import com.trips.pk.model.flight.Airport
import com.trips.pk.model.flight.Countries
import com.trips.pk.ui.common.KEY_AIRPORT_LIST
import com.trips.pk.ui.common.KEY_COUNTRIES_LIST
import com.trips.pk.utils.FileHelper
import java.lang.reflect.Type


class PrefRepository(private val app: Application) {

    private val prefs = app.getSharedPreferences(
        "APP_TAG", Context.MODE_PRIVATE
    )

    fun saveAirports(airport : List<Airport>){
        val userJson= Gson().toJson(airport)
        prefs.edit().putString(KEY_AIRPORT_LIST, userJson).apply()
    }

    fun getAirportsFromPrefRepository(): List<Airport>? {
        var airportItems: List<Airport>?=null
        try {
            val serializedObject=prefs.getString(KEY_AIRPORT_LIST, null)
            if (serializedObject != null) {
                val gson = Gson()
                val type: Type? = object : TypeToken<List<Airport?>?>() {}.type
                airportItems = gson.fromJson<List<Airport>>(serializedObject, type)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return airportItems
    }



    fun deleteAirportsFromPrefRepository()=prefs.edit().remove(KEY_AIRPORT_LIST).apply()


    fun getAirPortsFromResource() : List<Airport> {
        val jsonString = FileHelper.getTextFromResources(app, R.raw.airports)
        return Gson().fromJson(jsonString, Array<Airport>::class.java).asList()
    }

    fun saveCountries(country : List<Countries>){
        val userJson= Gson().toJson(country)
        prefs.edit().putString(KEY_COUNTRIES_LIST, userJson).apply()
    }

    fun getCountriesFromPrefRepository(): List<Countries>? {
        var countryItems: List<Countries>?=null
        try {
            val serializedObject=prefs.getString(KEY_COUNTRIES_LIST, null)
            if (serializedObject != null) {
                val gson = Gson()
                val type: Type? = object : TypeToken<List<Countries?>?>() {}.type
                countryItems = gson.fromJson<List<Countries>>(serializedObject, type)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return countryItems
    }

    fun deleteCountriesFromPrefRepository()=prefs.edit().remove(KEY_COUNTRIES_LIST).apply()

    fun getCountriesFromResources() : List<Countries> {
        val jsonString = FileHelper.getTextFromResources(app, R.raw.countries)
        return Gson().fromJson(jsonString, Array<Countries>::class.java).asList()
    }

}