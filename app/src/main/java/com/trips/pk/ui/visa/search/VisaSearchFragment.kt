package com.trips.pk.ui.visa.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentVisaSearchBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.visa.Visa
import com.trips.pk.model.visa.VisaCountries
import com.trips.pk.ui.common.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class VisaSearchFragment:Fragment() {
    private lateinit var binding:FragmentVisaSearchBinding
    private val mViewModel : VisaSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVisaSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Visas Search"
        binding.itemClickListener=OnVisaItemClickListener()

        binding.edEnterCountry.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().isNotEmpty()) {
                    showCountriesAdapter(s.toString())
                }
            }
        })

        binding.btnSearch.setOnClickListener {
            if (binding.edEnterCountry.text.toString().trim().isNullOrEmpty()){
                Toast.makeText(requireContext(),"Please enter country name",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val t = binding.edEnterCountry.text.toString().trim()
            var countryId = 0
            for (item in VISA_COUNTRIES){
                if (t==item.name){
                    tempVisaPlaceName = item.name
                    countryId = item.id
                    break
                }
            }
            val bundle = Bundle()
            bundle.putInt("countryId",countryId)
            findNavController().navigate(R.id.action_visa_search_to_visa_detail_fragment,bundle)
            binding.edEnterCountry.setText("")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }


    inner class OnVisaItemClickListener:OnListItemClickListener<VisaCountries>{
        override fun onItemClick(item: VisaCountries, pos: Int) {
            val bundle = Bundle()
            bundle.putInt("countryId",item.id)
            tempVisaPlaceName=item.name
            findNavController().navigate(R.id.action_visa_search_to_visa_detail_fragment,bundle)
        }

    }

    private fun showCountriesAdapter(t : String){
        val tempList = arrayListOf<VisaCountries>()

        var text = t.trim()
        tempList.clear()
        if (text.isNotEmpty()) {
            text = text.lowercase(Locale.getDefault())
            for (item in VISA_COUNTRIES) {
                if (item.name.lowercase(Locale.ROOT).contains(text)) {
                    tempList.add(item)
                }
            }
        }

        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, tempList)
        binding.edEnterCountry.setAdapter(adapter)
        binding.edEnterCountry.showDropDown()

//        binding.edEnterCountry.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
//            val selectedItem = parent.getItemAtPosition(position) as VisaCountries
//            tempVisaPlaceName=selectedItem.name
//        }
    }
}