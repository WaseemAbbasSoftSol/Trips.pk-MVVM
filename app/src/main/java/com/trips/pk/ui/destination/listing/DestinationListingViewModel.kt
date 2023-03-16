package com.trips.pk.ui.destination.listing

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.ViewModel
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.*
import com.trips.pk.ui.common.ADMIN_BASE_URL

class DestinationListingViewModel() : ViewModel() {

    private val priceDetail = arrayListOf<TourPackagePrices>(
        TourPackagePrices(
            50, 50000, 30, 20000, "", 5, "5/5/2023", "5/5/2023", "note is here", "4/4/2023",
            5, ""
        ),
    )

    private val tourStatus = TourStatus(3, true, "On Sale", 3)
    val country = Countries(4, "Pakistan", "44", "", true)
    val city = City(4, "Pakistan", "44", 4)
    private val itineraryDetails = arrayListOf(
        TourItineraryDetails("3", "Hunza packages", "Hunza"),
        TourItineraryDetails("3", "Hunza packages", "Hunza"),
        TourItineraryDetails("3", "Hunza packages", "Hunza")
    )

    val img="$ADMIN_BASE_URL\\images\\tourdetailimages\\20556592-fc4d-4879-a722-580d54378e29-download (44).jfif"
    val tempList = arrayListOf<TourDetail>(
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 7, "", "7 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 6, "", "5 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 6, "", "5 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 6, "", "5 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 6, "", "5 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
        TourDetail(0, "07 Days Trip Skardu", priceDetail, img, "", 6, "", "5 Days trips to Skardu", "Essential Heading", "essential detail", true, 2, 11, tourStatus, country, 4, city, itineraryDetails),
       )
}