package com.trips.pk.ui.rent_a_car.listing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.databinding.FragmentRentACarSearchResultBinding
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.rent_a_car.dialogs.CarOptionsAdapter
import com.trips.pk.ui.rent_a_car.dialogs.RentACarBookBottomsheet

class RentACarSearchResultFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentRentACarSearchResultBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRentACarSearchResultBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Search Result"
        var adapter= CarOptionsAdapter(requireContext(),this,1,9)
        val layoutManager= LinearLayoutManager(requireContext())
        binding.rvRentACarResult.layoutManager=layoutManager
        binding.rvRentACarResult.adapter=adapter

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onDummyClick() {
    val dialog=RentACarBookBottomsheet()
        dialog.show(parentFragmentManager, APP_TAG)
    }
}