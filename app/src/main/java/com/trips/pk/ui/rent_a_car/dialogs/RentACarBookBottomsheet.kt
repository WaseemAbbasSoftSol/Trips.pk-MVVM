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
import com.trips.pk.databinding.BottomsheetRentACarBookBinding
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.utils.Helpers

class RentACarBookBottomsheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetRentACarBookBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetRentACarBookBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        binding.btnBook.setOnClickListener {
            findNavController().navigate(R.id.action_global_to_rent_a_car_booking_detail_fragment)
            dismiss()
        }
        return binding.root
    }

}