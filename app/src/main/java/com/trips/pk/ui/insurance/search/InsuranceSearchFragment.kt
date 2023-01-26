package com.trips.pk.ui.insurance.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentInsuranceSearchBinding

class InsuranceSearchFragment:Fragment() {
    private lateinit var binding:FragmentInsuranceSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInsuranceSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Insurance Search"
        return binding.root
    }
}