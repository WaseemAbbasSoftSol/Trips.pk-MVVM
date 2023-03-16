package com.trips.pk.ui.agent.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentAgentSearchBinding
import com.trips.pk.model.hajj.Temp
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentSearchFragment:Fragment() {
    private lateinit var binding:FragmentAgentSearchBinding
    private val mViewModel:AgentSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAgentSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Agents Search"
        binding.btnSearch.setOnClickListener { Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show() }
        binding.agentClickListener=OnAgentItemClickListener()
        binding.cityClickListener=OnCityClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnAgentItemClickListener:OnListItemClickListener<Temp> {
        override fun onItemClick(item: Temp, pos: Int) {
            findNavController().navigate(R.id.action_agent_search_to_listing_fragment)
        }
    }

    inner class OnCityClickListener:OnListItemClickListener<Temp> {
        override fun onItemClick(item: Temp, pos: Int) {
            findNavController().navigate(R.id.action_agent_search_to_listing_fragment)
        }
    }
}