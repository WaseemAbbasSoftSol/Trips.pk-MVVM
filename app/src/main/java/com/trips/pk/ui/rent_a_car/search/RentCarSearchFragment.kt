package com.trips.pk.ui.rent_a_car.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentRentACarSearchBinding
import com.trips.pk.ui.common.DummyClickListener

class RentCarSearchFragment: Fragment(), DummyClickListener {
    private lateinit var binding:FragmentRentACarSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRentACarSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Rent a Car Search"
        val adapter=CarsVarietyAdapter(requireContext(),this,0,7)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarVariety.layoutManager=layoutManager
        binding.rvCarVariety.adapter=adapter

        val adCompany=CarsVarietyAdapter(requireContext(),this,1,4)
        val layoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarCompany.layoutManager=layoutManager1
        binding.rvCarCompany.adapter=adCompany

        val adType=CarsVarietyAdapter(requireContext(),this,2,3)
        val layoutManager2 = GridLayoutManager(requireContext(),3)
        binding.rvVehicalType.layoutManager=layoutManager2
        binding.rvVehicalType.adapter=adType

        binding.tvViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_rent_a_car_search_to_rent_a_car_list_fragment)
        }
        return binding.root
    }

    override fun onDummyClick() {
       Toast.makeText(requireContext(),"Varieties are coming soon",Toast.LENGTH_SHORT).show()
    }
}