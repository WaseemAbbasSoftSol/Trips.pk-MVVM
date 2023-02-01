package com.trips.pk.ui.tour.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.databinding.FragmentToursSearchBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.CountryAndCityTogether
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TourSearchFragment: Fragment() {
    private lateinit var binding:FragmentToursSearchBinding
    private val mViewModel:TourSearchViewModel by viewModel()
    val countryCity = arrayListOf<CountryAndCityTogether>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentToursSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Tours Search"
        binding.tvViewWorldwide.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("world"))
        }
        binding.tvViewPak.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("pak"))
        }
//        binding.edSearch.setOnClickListener {
//            val dialog = SearchTourPlacesBottomSheet()
//            dialog.show(parentFragmentManager, APP_TAG)
//            dialog.setSelectedLocation(object : LocationSelectedListener<CountryAndCityTogether>{
//                override fun onLocationSelected(item: CountryAndCityTogether, position: Int) {
//                   binding.edSearch.setText(item.name)
//                    searchedKeyWord = item.name
//                }
//
//            })
//        }

        binding.edSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isNotEmpty()) {
                   showAdapter(s.toString())
                }
            }
        })

        binding.countryClickListener=OnCountryItemClickListener()
        binding.cityClickListener=OnPakCityClickListener()

        binding.btnSearch.setOnClickListener {
            val search = binding.edSearch.text.toString().trim()
            if (search.isNullOrEmpty()){
                Toast.makeText(requireContext(),"Please enter location",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourListing(search))
            binding.edSearch.setText("")
            countryCity.clear()
        }
        return binding.root
    }

    private fun showAdapter(t : String){
        val tempList = arrayListOf<CountryAndCityTogether>()

        var text = t.trim()
        tempList.clear()
        if (text.isNotEmpty()) {
            text = text.lowercase(Locale.getDefault())
            for (item in countryCity) {
                if (item.name.lowercase(Locale.ROOT).contains(text)) {
                    tempList.add(item)
                }
            }
        }

        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, tempList)
        binding.edSearch.setAdapter(adapter)
        binding.edSearch.showDropDown()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        getCountriesAndCitiesTogether()

    }

    inner class OnCountryItemClickListener:OnListItemClickListener<Countries>{
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourListing(item.name))
        }
    }
    inner class OnPakCityClickListener : OnListItemClickListener<Countries>{
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourListing(item.name))
        }

    }

    private fun getCountriesAndCitiesTogether(){
        countryCity.clear()
        for (item in COUNTRIES_WITH_PAK_CITIES){
            if (item.id == 157){
                for (i in item.cities){
                    countryCity.add(CountryAndCityTogether(i.id,i.name,i.code))
                }
            }
        }
        for (item in COUNTRIES_WITH_PAK_CITIES){
            countryCity.add(CountryAndCityTogether(item.id,item.name,item.code))
        }
    }
}