package com.trips.pk.ui.destination.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomsheetDestinationDetailBinding
import com.trips.pk.databinding.BottomsheetHajjPackageBinding
import com.trips.pk.utils.Helpers

class DestinationDetailBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetDestinationDetailBinding
private var placeName=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle=this.arguments
        placeName= bundle!!.getString("placeName").toString()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= BottomsheetDestinationDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        binding.tvPlaceName.text=placeName

        return binding.root
    }

}