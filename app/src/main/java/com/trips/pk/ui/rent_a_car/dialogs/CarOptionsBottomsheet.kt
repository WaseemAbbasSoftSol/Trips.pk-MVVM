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
import com.trips.pk.ui.common.mVehicles
import com.trips.pk.utils.Helpers

class CarOptionsBottomsheet: BottomSheetDialogFragment(),DummyClickListener,CarOptionsAdapter.VehicleModelClickListener {

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
            var adapter=CarOptionsAdapter(requireContext(),this,111,vehicleModels.size,vehicleModels, modelListener = this)
            val layoutManager=GridLayoutManager(requireContext(),2)
            binding.rvCarOption.layoutManager=layoutManager
            binding.rvCarOption.adapter=adapter
            val price=getLeastPrice(1)
            val duration = getLeastPrice(0)
            binding.tvMoney.text = price
            binding.tvPerDay.text="/$duration"
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

    private fun getLeastPrice(which:Int):String{
        var p = 0
        var duration = ""
        try {
            for (item in vehicleModels){
                for (dirtyFlag in item.vehicles){
                    for (sub in dirtyFlag.vehiclesPrices){
                        if (p==0){
                            p=sub.price
                            duration=sub.duration
                        }
                        else if (sub.price<p){
                            p=sub.price
                            duration=sub.duration
                        }
                    }

                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return when(which){
            1 ->  "Rs.$p"
            else -> duration
        }

    }

    override fun onModelClick(item: VehiclesModel, position: Int) {
        mVehicles.clear()
        mVehicles.addAll(item.vehicles)
        findNavController().navigate(R.id.action_global_to_rent_a_car_search_result_fragment)
        dismiss()
    }
}