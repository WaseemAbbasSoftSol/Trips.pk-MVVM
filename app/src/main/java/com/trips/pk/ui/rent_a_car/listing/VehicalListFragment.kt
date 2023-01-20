package com.trips.pk.ui.rent_a_car.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentVehicalListBinding
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.rent_a_car.dialogs.CarOptionsBottomsheet
import com.trips.pk.ui.rent_a_car.search.CarsVarietyAdapter

class VehicalListFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentVehicalListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVehicalListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        binding.tvToolbar.text = "Find a Vehical"
        binding.tvDes.text = "type to suit your need"
        val adType= CarsVarietyAdapter(requireContext(),this,2,10)
        val layoutManager2 = GridLayoutManager(requireContext(),3)
        binding.rvVehicalType.layoutManager=layoutManager2
        binding.rvVehicalType.adapter=adType

        return binding.root
    }

    override fun onDummyClick() {
        val dialog= CarOptionsBottomsheet()
        dialog.show(parentFragmentManager, APP_TAG)
    }
}