package com.trips.pk.ui.flight.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookConfirmedBinding

class FlightBookConfirmedFragment: Fragment() {
    private lateinit var binding:FragmentFlightBookConfirmedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookConfirmedBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Information Received"
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_global_to_flight_search_fragment)
        }
        binding.toolbarLayout.cvBack.setOnClickListener {

        }
        return binding.root
    }
}