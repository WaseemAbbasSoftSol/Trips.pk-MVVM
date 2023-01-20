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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomSheetCarOptionBinding
import com.trips.pk.databinding.BottomSheetRentACarCategoryBinding
import com.trips.pk.databinding.BottomsheetRentACarBookBinding
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.utils.Helpers

class CarCategoryBottomsheet: BottomSheetDialogFragment(),DummyClickListener {

    private lateinit var binding: BottomSheetRentACarCategoryBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetRentACarCategoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)

        val adapter=CarCategoryAdapter(requireContext(),this)
        val layoutManager=LinearLayoutManager(requireContext())
        binding.rvCarCategory.layoutManager=layoutManager
        binding.rvCarCategory.adapter=adapter

        binding.btnDone.setOnClickListener {
           Toast.makeText(requireContext(),"Feature is coming soon",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onDummyClick() {
        Toast.makeText(requireContext(),"economy is selected",Toast.LENGTH_SHORT).show()
    }

}