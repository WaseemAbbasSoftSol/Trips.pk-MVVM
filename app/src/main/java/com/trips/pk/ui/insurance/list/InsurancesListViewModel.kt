package com.trips.pk.ui.insurance.list

import androidx.lifecycle.ViewModel
import com.trips.pk.R
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.insurance.Insurance

class InsurancesListViewModel(private val repository: TripsRepository):ViewModel() {

    val agentList= arrayListOf<Insurance>(
        Insurance(R.drawable.img_igi,"IGI Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"Adamjee Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"EFU Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"IGI Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"Adamjee Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"EFU Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"IGI Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"Adamjee Travel Insurance", "", ""),
        Insurance(R.drawable.img_igi,"EFU Travel Insurance", "", ""),
    )
}