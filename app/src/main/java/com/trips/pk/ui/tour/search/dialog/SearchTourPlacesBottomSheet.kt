package com.trips.pk.ui.tour.search.dialog

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
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.CountryAndCityTogether
import com.trips.pk.ui.common.AIRPORT_LIST
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.LocationSelectedListener
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.flight.dialogs.origin.SearchOriginDestinationAdapter
import com.trips.pk.utils.Helpers

class SearchTourPlacesBottomSheet: BottomSheetDialogFragment(),OnListItemClickListener<CountryAndCityTogether> {

    private lateinit var binding:BottomSheetOriginLayoutBinding
    private var adapter: SearchTourPlacesAdapter?=null
    private var list= arrayListOf<CountriesWithCities>()
    private var listener: LocationSelectedListener<CountryAndCityTogether>?=null

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
        list.addAll(COUNTRIES_WITH_PAK_CITIES)
        binding.edSearchOrigin.text.clear()
        binding.edSearchOrigin.requestFocus()


        binding.edSearchOrigin.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isNotEmpty()) {
                    adapter!!.filter(s.toString())
                }
            }
        })
        val countryCity = arrayListOf<CountryAndCityTogether>()
        countryCity.clear()

        for (item in list){
            if (item.id == 157){
                for (i in item.cities){
                    countryCity.add(CountryAndCityTogether(i.id,i.name,i.code))
                }
            }
        }
        for (item in list){
            countryCity.add(CountryAndCityTogether(item.id,item.name,item.code))
        }

        adapter= SearchTourPlacesAdapter(requireContext(), countryCity,this)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchOrigin.layoutManager = layoutManager
        binding.rvSearchOrigin.setHasFixedSize(true)
        binding.rvSearchOrigin.adapter=adapter

        return binding.root
    }


    override fun onItemClick(item: CountryAndCityTogether, pos: Int) {
        listener!!.onLocationSelected(item, pos)
        binding.edSearchOrigin.text.clear()
        dismiss()
    }

    fun setSelectedLocation(listener: LocationSelectedListener<CountryAndCityTogether>){
        this.listener=listener
    }

    override fun onDestroy() {
        super.onDestroy()
        list.clear()
        binding.edSearchOrigin.text.clear()
    }
}