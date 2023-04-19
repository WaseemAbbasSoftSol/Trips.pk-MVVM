package com.trips.pk.ui.hajj.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentHajjDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HajjDetailsFragment:Fragment() {
    private lateinit var binding:FragmentHajjDetailsBinding
    private val mViewModel:HajjDetailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHajjDetailsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Hajj/Umrah"
        binding.btnBook.setOnClickListener { findNavController().navigate(R.id.action_hajj_detail_to_booking_fragment) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
}