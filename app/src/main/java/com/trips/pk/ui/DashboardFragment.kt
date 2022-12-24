package com.trips.pk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentDashboardBinding

class DashboardFragment:Fragment() {
    private lateinit var binding:FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

      //  binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card_unselected)

        binding.clFlight.setOnClickListener{
         //   binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card)
            findNavController().navigate(R.id.action_dashboard_to_flight_search_fragment)
        }

        return binding.root
    }
}