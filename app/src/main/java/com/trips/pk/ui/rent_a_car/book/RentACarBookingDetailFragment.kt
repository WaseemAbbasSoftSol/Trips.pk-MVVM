package com.trips.pk.ui.rent_a_car.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentRentACarBookingDetailBinding

class RentACarBookingDetailFragment:Fragment() {
    private lateinit var binding:FragmentRentACarBookingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRentACarBookingDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Rent a Car Booking"
        return binding.root
    }
}