package com.trips.pk.ui.insurance.search

import androidx.lifecycle.ViewModel
import com.trips.pk.R
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.insurance.Insurance

class InsuranceSearchViewModel(
    private val repository:TripsRepository
):ViewModel() {

    val insuranceList= arrayListOf<Insurance>(
        Insurance(R.drawable.im_domestic,"Domestic", "2 Insurance", "Rs:5,000"),
        Insurance(R.drawable.im_worldwide,"Worldwide", "7 Insurance", "Rs:7,000"),
        Insurance(R.drawable.im_schengen,"Schengen", "5 Insurance", "Rs:8,000"),
    )

    val agentList= arrayListOf<Insurance>(
        Insurance(R.drawable.img_igi,"IGI Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"Adamjee Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"EFU Travel Insurance", "", ""),
    )
}