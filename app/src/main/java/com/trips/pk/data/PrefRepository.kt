package com.trips.pk.data

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.trips.pk.R
import com.trips.pk.model.Airport
import com.trips.pk.utils.FileHelper

class PrefRepository(private val app: Application) {

    private val prefs = app.getSharedPreferences(
        "APP_TAG", Context.MODE_PRIVATE
    )


    fun getAirPorts() : List<Airport> {
        val jsonString = FileHelper.getTextFromResources(app, R.raw.airports)
        return Gson().fromJson(jsonString, Array<Airport>::class.java).asList()
    }

}