package com.trips.pk.ui.insurance.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentInsurancesBinding
import com.trips.pk.model.insurance.Insurance
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsurancesListFragment:Fragment() {
    private lateinit var binding:FragmentInsurancesBinding
    private val mViewModel:InsurancesListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentInsurancesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.itemClickListener=OnInsuranceItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnInsuranceItemClickListener: OnListItemClickListener<Insurance> {
        override fun onItemClick(item: Insurance, pos: Int) {
            Toast.makeText(requireContext(),"Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}