package com.trips.pk.ui.rent_a_car.listing

import android.annotation.SuppressLint
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
import com.trips.pk.databinding.FragmentRentACarSearchResultBinding
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.ui.common.*
import com.trips.pk.ui.rent_a_car.dialogs.CarOptionsAdapter
import com.trips.pk.ui.rent_a_car.dialogs.RentACarBookBottomsheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class RentACarSearchResultFragment:Fragment() {
    private lateinit var binding:FragmentRentACarSearchResultBinding
    private val mViewModel:RentACarSearchResultViewModel by viewModel()
    private var categoryId = 0
    private var cityId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args = RentACarSearchResultFragmentArgs.fromBundle(it!!)
            categoryId = args.categoryId
            cityId = args.cityId
            if (categoryId!=0 && cityId !=0){
                mViewModel.searchVehicle(categoryId,cityId)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRentACarSearchResultBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Search Result"

   /*     var adapter= CarOptionsAdapter(requireContext(),this,1,9)
        val layoutManager= LinearLayoutManager(requireContext())
        binding.rvRentACarResult.layoutManager=layoutManager
        binding.rvRentACarResult.adapter=adapter*/

        binding.vehicleClickListener=OnVehicleClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnVehicleClickListener:OnItemViewClickListener<Vehicle> {
        override fun onClick(view: View, item: Vehicle) {
            mVehiclePrice = item
            val dialog=RentACarBookBottomsheet()
            dialog.show(parentFragmentManager, APP_TAG)
            dialog.setButtonListener(object : RentACarBookBottomsheet.ButtonClickListener{
                override fun onClicked(which: Boolean) {
                    if (which){
                        findNavController().navigate(R.id.action_global_to_rent_a_car_booking_detail_fragment)
                    }
                }
            })
        }
    }
}