package com.trips.pk.ui.flight.dialogs.origin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trips.pk.R
import com.trips.pk.databinding.BottomSheetOriginLayoutBinding
import com.trips.pk.model.flight.Airport
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.common.airPortList
import com.trips.pk.utils.Helpers

class SearchOriginDestinationBottomSheet: BottomSheetDialogFragment(),OnListItemClickListener<Airport> {

private lateinit var binding:BottomSheetOriginLayoutBinding
private var adapter: SearchOriginDestinationAdapter?=null
private var list= arrayListOf<Airport>()
    private var isOrigin=true
    private var listener: LocationSelectedListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        isOrigin = bundle!!.getBoolean("isOrigin")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= BottomSheetOriginLayoutBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        Helpers.makeBottomSheetRounded(binding.root, dialog!!)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        list.clear()
        binding.edSearchOrigin.text.clear()
        binding.edSearchOrigin.requestFocus()
        list.addAll(airPortList)

        binding.edSearchOrigin.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isNotEmpty()) {
                    adapter!!.filter(s.toString())
                }
            }
        })
        adapter= SearchOriginDestinationAdapter(requireContext(), list,this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchOrigin.layoutManager = layoutManager
        binding.rvSearchOrigin.setHasFixedSize(true)
        binding.rvSearchOrigin.adapter=adapter

        return binding.root
    }


    override fun onItemClick(item: Airport, pos: Int) {
        listener!!.onLocationSelected(item, isOrigin)
        binding.edSearchOrigin.text.clear()
        dismiss()
    }

    interface LocationSelectedListener{
        fun onLocationSelected(airport: Airport, isOrigin:Boolean)
    }
    fun setSelectedLocation(listener: LocationSelectedListener){
        this.listener=listener
    }

    override fun onDestroy() {
        super.onDestroy()
        list.clear()
        binding.edSearchOrigin.text.clear()
    }
}