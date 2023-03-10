package com.trips.pk.ui.insurance.agent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentInsuranceAgentListBinding
import com.trips.pk.model.insurance.Insurance
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsuranceAgentListFragment:Fragment() {
    private lateinit var binding:FragmentInsuranceAgentListBinding
    private val mViewModel:InsuranceAgentListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInsuranceAgentListBinding.inflate(inflater,container, false)
        binding.lifecycleOwner=this
        binding.agentClickListener=OnInsuranceAgentClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
    inner class OnInsuranceAgentClickListener: OnListItemClickListener<Insurance> {
        override fun onItemClick(item: Insurance, pos: Int) {
            Toast.makeText(requireContext(),"Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}