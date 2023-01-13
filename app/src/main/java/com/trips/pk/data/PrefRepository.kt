package com.trips.pk.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.trips.pk.R
import com.trips.pk.model.flight.Airport
import com.trips.pk.model.flight.Countries
import com.trips.pk.utils.FileHelper

class PrefRepository(private val app: Application) {

    private val prefs = app.getSharedPreferences(
        "APP_TAG", Context.MODE_PRIVATE
    )


    fun getAirPorts() : List<Airport> {
        val jsonString = FileHelper.getTextFromResources(app, R.raw.airports)
        return Gson().fromJson(jsonString, Array<Airport>::class.java).asList()
    }

    fun getCountries() : List<Countries> {
        val jsonString = FileHelper.getTextFromResources(app, R.raw.countries)
        return Gson().fromJson(jsonString, Array<Countries>::class.java).asList()
    }

}