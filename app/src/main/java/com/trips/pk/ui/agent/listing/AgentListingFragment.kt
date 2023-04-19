package com.trips.pk.ui.agent.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentAgentListingBinding
import com.trips.pk.model.hajj.Temp
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentListingFragment:Fragment() {
    private lateinit var binding:FragmentAgentListingBinding
    private val mViewModel:AgentListingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAgentListingBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.itemClickListener=OnAgentListItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnAgentListItemClickListener:OnListItemClickListener<Temp> {
        override fun onItemClick(item: Temp, pos: Int) {
            findNavController().navigate(R.id.action_agent_list_to_agent_base_fragment)
        }
    }
}