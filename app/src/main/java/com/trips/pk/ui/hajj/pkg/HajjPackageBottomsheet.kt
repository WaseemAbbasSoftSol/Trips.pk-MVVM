package com.trips.pk.ui.hajj.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomsheetHajjPackageBinding
import com.trips.pk.utils.Helpers

class HajjPackageBottomsheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetHajjPackageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= BottomsheetHajjPackageBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding.btnViewDetail.setOnClickListener {
            findNavController().navigate(R.id.action_hajj_list_to_detail_fragment)
            dismiss()
        }
        return binding.root
    }

}