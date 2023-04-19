package com.trips.pk.ui.insurance.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentInsuranceSearchBinding
import com.trips.pk.model.insurance.Insurance
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsuranceSearchFragment:Fragment() {
    private lateinit var binding:FragmentInsuranceSearchBinding
    private val mViewModel:InsuranceSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInsuranceSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Insurance Search"
        binding.itemClickListener=OnInsuranceItemClickListener()
        binding.agentClickListener=OnInsuranceAgentClickListener()
        binding.tvViewAll.setOnClickListener {
            findNavController().navigate(R.id.action_insurance_search_to_insurance_agent_list_fragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnInsuranceItemClickListener:OnListItemClickListener<Insurance> {
        override fun onItemClick(item: Insurance, pos: Int) {
            findNavController().navigate(R.id.action_insurance_search_to_insurance_list_fragment)
        }
    }
    inner class OnInsuranceAgentClickListener:OnListItemClickListener<Insurance> {
        override fun onItemClick(item: Insurance, pos: Int) {
            findNavController().navigate(R.id.action_insurance_search_to_insurance_list_fragment)
        }
    }
}