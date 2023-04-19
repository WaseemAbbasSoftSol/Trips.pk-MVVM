package com.trips.pk.ui.rent_a_car.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentVehicalListBinding
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.mVehicleCategories
import com.trips.pk.ui.rent_a_car.dialogs.CarOptionsBottomsheet
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class VehicalListFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentVehicalListBinding
    private val mViewModel : VehicleListViewModel by viewModel {
        parametersOf(vehicleCategories)
    }
    private var vehicleCategories = arrayListOf<VehicleCategory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       vehicleCategories.addAll(mVehicleCategories)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVehicalListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        binding.tvToolbar.text = "Find a Vehical"
        binding.tvDes.text = "type to suit your need"

      //  val adType= CarsVarietyAdapter(requireContext(),this,2,10)
       // val layoutManager2 = GridLayoutManager(requireContext(),3)
       // binding.rvVehicalType.layoutManager=layoutManager2
       // binding.rvVehicalType.adapter=adType

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
    override fun onDummyClick() {
        val dialog= CarOptionsBottomsheet()
        dialog.show(parentFragmentManager, APP_TAG)
    }
}