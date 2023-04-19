package com.trips.pk.ui.hajj.list.view_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentHajjViewAllBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HajjListViewAllFragment:Fragment() {
    private lateinit var binding:FragmentHajjViewAllBinding
    private val mViewModel:HajjListViewAllViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHajjViewAllBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
}