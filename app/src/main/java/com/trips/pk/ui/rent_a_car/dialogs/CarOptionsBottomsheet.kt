package com.trips.pk.ui.rent_a_car.dialogs

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomSheetCarOptionBinding
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.mVehicleModels
import com.trips.pk.utils.Helpers

class CarOptionsBottomsheet: BottomSheetDialogFragment(),DummyClickListener {

    private lateinit var binding: BottomSheetCarOptionBinding

    private var vehicleModels= arrayListOf<VehiclesModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetCarOptionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        vehicleModels.addAll(mVehicleModels)
        if (vehicleModels.size>=1){
            var adapter=CarOptionsAdapter(requireContext(),this,111,vehicleModels.size,vehicleModels)
            val layoutManager=GridLayoutManager(requireContext(),2)
            binding.rvCarOption.layoutManager=layoutManager
            binding.rvCarOption.adapter=adapter
        }else{
            var adapter=CarOptionsAdapter(requireContext(),this,0,9)
            val layoutManager=GridLayoutManager(requireContext(),2)
            binding.rvCarOption.layoutManager=layoutManager
            binding.rvCarOption.adapter=adapter
        }



        return binding.root
    }

    override fun onDummyClick() {
       findNavController().navigate(R.id.action_global_to_rent_a_car_search_result_fragment)
        dismiss()
    }
}