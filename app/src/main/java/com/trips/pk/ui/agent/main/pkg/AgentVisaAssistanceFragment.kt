package com.trips.pk.ui.agent.main.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentAgentVisaAssistanceBinding
import com.trips.pk.model.hajj.Temp
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgentVisaAssistanceFragment:Fragment() {
    private lateinit var binding:FragmentAgentVisaAssistanceBinding
    private val mViewModel:AgentVisaAssistanceViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAgentVisaAssistanceBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.itemClickListener=OnVisaAssistanceItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnVisaAssistanceItemClickListener:OnListItemClickListener<Temp> {
        override fun onItemClick(item: Temp, pos: Int) {
            Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
        }
    }
}