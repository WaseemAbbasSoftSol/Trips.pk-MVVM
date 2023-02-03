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
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomSheetCarOptionBinding
import com.trips.pk.databinding.BottomsheetRentACarBookBinding
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.model.rent_a_car.VehiclesPrice
import com.trips.pk.ui.common.ADMIN_BASE_URL
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.mVehiclePrice
import com.trips.pk.utils.Helpers

class RentACarBookBottomsheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetRentACarBookBinding

    private var vehiclePrices:Vehicle?=null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetRentACarBookBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        vehiclePrices= mVehiclePrice
        vehiclePrices.let {
            if (it!=null){
                val img = if (it.image.isNullOrEmpty() || it.image=="N/A") "" else "$ADMIN_BASE_URL${it.image}"
                Glide.with(requireContext()).load(img).into(binding.ivCar)
                binding.tvCarName.text = it.name
                val inCityPrice = vehiclePrices!!.vehiclesPrices[0]
                val incityWithDriver = if (inCityPrice.withDriver) "with driver" else "without driver"
                var p = "${inCityPrice.duration} = Rs.${inCityPrice.price} ${inCityPrice.zone} $incityWithDriver"
                binding.tvInCity.text = p

                if (vehiclePrices!!.vehiclesPrices.size>1){
                    val outCityPrice = vehiclePrices!!.vehiclesPrices[1]
                    val outCityWithDriver = if (outCityPrice.withDriver) "with driver" else "without driver"
                    var q = "${outCityPrice.duration} = Rs.${outCityPrice.price} ${outCityPrice.zone} $outCityWithDriver"
                    binding.tvOutCity.text = q
                    binding.tvOutCity.visibility=View.VISIBLE
                }
            }

        }

        binding.btnBook.setOnClickListener {
            findNavController().navigate(R.id.action_global_to_rent_a_car_booking_detail_fragment)
            dismiss()
        }
        return binding.root
    }

}