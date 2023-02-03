package com.trips.pk.ui.rent_a_car.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentRentACarSearchBinding
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.ui.common.*
import com.trips.pk.ui.rent_a_car.dialogs.CarCategoryBottomsheet
import com.trips.pk.ui.rent_a_car.dialogs.CarOptionsBottomsheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentCarSearchFragment: Fragment(), DummyClickListener {
    private lateinit var binding:FragmentRentACarSearchBinding
    private val mViewModel : RentCarSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRentACarSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Rent a Car Search"

       /* val adapter=CarsVarietyAdapter(requireContext(),this,0,7)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarVariety.layoutManager=layoutManager
        binding.rvCarVariety.adapter=adapter*/

        val adCompany=CarsVarietyAdapter(requireContext(),this,1,4)
        val layoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarCompany.layoutManager=layoutManager1
        binding.rvCarCompany.adapter=adCompany

       /* val adType=CarsVarietyAdapter(requireContext(),this,2,3)
        val layoutManager2 = GridLayoutManager(requireContext(),3)
        binding.rvVehicalCategory.layoutManager=layoutManager2
        binding.rvVehicalCategory.adapter=adType*/

        binding.tvViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_rent_a_car_search_to_rent_a_car_list_fragment)
        }
        binding.edEnterCategory.setOnClickListener {
            val dialog=CarCategoryBottomsheet()
            dialog.show(parentFragmentManager, APP_TAG)
        }
        binding.vehicleCategoryClickListener = OnVehicleCategoryClickListener()
        binding.vehicleModelClickListener = OnVehicleModelClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.vehicleCategory.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                mVehicleCategories.clear()
                mVehicleCategories.addAll(it)
            }
        }
    }

    inner class OnVehicleCategoryClickListener : OnListItemClickListener<VehicleCategory> {
        override fun onItemClick(item: VehicleCategory, pos: Int) {
            val dialog=CarOptionsBottomsheet()
            mVehicleModels.clear()
            mVehicleModels.addAll(item.vehiclesModels)
            dialog.show(parentFragmentManager, APP_TAG)
        }
    }

    inner class OnVehicleModelClickListener : OnListItemClickListener<VehiclesModel> {
        override fun onItemClick(item: VehiclesModel, pos: Int) {
            mVehicles.clear()
            mVehicles.addAll(item.vehicles)
            findNavController().navigate(R.id.action_global_to_rent_a_car_search_result_fragment)
        }
    }

    override fun onDummyClick() {
       val dialog=CarOptionsBottomsheet()
        dialog.show(parentFragmentManager, APP_TAG)
    }

}