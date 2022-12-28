package com.trips.pk.ui.flight.search

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aminography.primecalendar.civil.CivilCalendar

import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.aminography.primedatepicker.picker.theme.LightThemeFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightSearchBinding
import com.trips.pk.model.Airport
import com.trips.pk.model.FlightSearch
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.mFromTo
import com.trips.pk.ui.common.mTourType
import com.trips.pk.ui.dialogs.origin.DateRangePickerBottomSheet
import com.trips.pk.ui.dialogs.origin.SearchOriginDestinationBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class FlightSearchFragment:Fragment() {
    private lateinit var binding:FragmentFlightSearchBinding
    private val mViewModel:FlightSearchViewModel by viewModel()
    private var noOfAdult=1
    private var noOfChild=0
    private var noOfInfant=0


    private var origin=""
    private var destination=""

    private var originName=""
    private var destinationName=""

    private var fromDate=""
    private var toDate=""

    private var tourType="round"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Flight Search"

        val dialog= SearchOriginDestinationBottomSheet()
        val bundle=Bundle()

        binding.radioOneWay.setOnClickListener {
            tourType="oneway"
            mTourType="oneway"
        }
        binding.radioReturn.setOnClickListener {
            tourType="round"
            mTourType="round"
        }
        binding.radioMultiDestination.setOnClickListener {
            tourType="multi"
            mTourType="multi"
        }

        binding.edOrigin.setOnClickListener {
            bundle.putBoolean("isOrigin", true)
            dialog.arguments=bundle
       dialog.show(parentFragmentManager, APP_TAG)
        }

        binding.edFinal.setOnClickListener {
            bundle.putBoolean("isOrigin", false)
            dialog.arguments=bundle
            dialog.show(parentFragmentManager, APP_TAG)
        }

        dialog.setSelectedLocation(object : SearchOriginDestinationBottomSheet.LocationSelectedListener{
            override fun onLocationSelected(airport: Airport, isOrigin: Boolean) {
                if (isOrigin){
                    binding.edOrigin.setText(airport.airportName)
                    origin=airport.airportCode
                    originName=airport.airportName
                    binding.edOrigin.error = null
                }
                else{
                    destination=airport.airportCode
                    binding.edFinal.setText(airport.airportName)
                    destinationName=airport.airportName
                    binding.edFinal.error = null
                }
            }

        })

        binding.edFromDate.setOnClickListener {
           //showDateRangePicker()
            val bundle=Bundle()
            bundle.putString("tourType", tourType)
            val dialog= DateRangePickerBottomSheet()
            dialog.arguments=bundle
            dialog.show(parentFragmentManager, APP_TAG)
            dialog.setSelectedRange(object : DateRangePickerBottomSheet.OnDateRangeSelected{
                @RequiresApi(Build.VERSION_CODES.O)
                override fun selectedRange(start: kotlin.String, end: kotlin.String, isToday:Boolean) {
                    if (start.isNotEmpty()){
                        fromDate=start
                        binding.edFromDate.setText(start.toString())
                        binding.edFromDate.error = null
                        if (isToday)fromDate=fromDate+"T"+getCurrentTimeAheadThreeMinutes()
                    }
                    if (end.isNotEmpty()){
                        toDate=end
                        binding.edToDate.setText(end.toString())
                    }
                }

            })
        }

        binding.edToDate.setOnClickListener {
            if (tourType=="oneway"){
                return@setOnClickListener
            }
            val bundle=Bundle()
            bundle.putString("tourType", tourType)
           // showDateRangePicker()
            val dialog= DateRangePickerBottomSheet()
            dialog.arguments=bundle
            dialog.show(parentFragmentManager, APP_TAG)
            dialog.setSelectedRange(object : DateRangePickerBottomSheet.OnDateRangeSelected{
                @RequiresApi(Build.VERSION_CODES.O)
                override fun selectedRange(start: kotlin.String, end: kotlin.String, isToday:Boolean) {
                    if (start.isNotEmpty()){
                        fromDate=start
                        binding.edFromDate.setText(start.toString())
                        binding.edFromDate.error = null
                        if (isToday)fromDate=fromDate+"T"+getCurrentTimeAheadThreeMinutes()
                    }
                    if (end.isNotEmpty()){
                        toDate=end
                        binding.edToDate.setText(end.toString())
                    }
                }

            })
        }

        binding.edAdult.setOnClickListener {
            showAgeRangePickerDialog()
        }
        binding.edChild.setOnClickListener {
            showAgeRangePickerDialog()
        }
        binding.edInfant.setOnClickListener {
            showAgeRangePickerDialog()
        }

        binding.btnSearch.setOnClickListener {
            if (validateFields()){
                if (tourType=="oneway") toDate=""
                val flightSearch=FlightSearch(origin, destination, fromDate, toDate, noOfAdult, noOfChild, noOfInfant, tourType)
                val bundle=Bundle()
                mFromTo = "$originName-$destinationName"
                bundle.putSerializable("search",flightSearch)
                findNavController().navigate(R.id.action_flight_search_to_flight_list_fragment, bundle)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }


    private fun showAgeRangePickerDialog(){
        try {
            val view: View = layoutInflater.inflate(R.layout.choose_adult_bottomsheet, null)
            val dialog = BottomSheetDialog(requireActivity(), R.style.DialogCustomTheme)
            dialog.setContentView(view)

            val btnDone=dialog.findViewById<AppCompatButton>(R.id.btn_done)

            val btnAdultUp=dialog.findViewById<ImageView>(R.id.iv_up_adult)
            val btnAdultDown=dialog.findViewById<ImageView>(R.id.iv_down_adult)
            val btnChildUp=dialog.findViewById<ImageView>(R.id.iv_up_child)
            val btnChildDown=dialog.findViewById<ImageView>(R.id.iv_down_child)
            val btnInfantUp=dialog.findViewById<ImageView>(R.id.iv_up_infant)
            val btnInfantDown=dialog.findViewById<ImageView>(R.id.iv_down_infant)

            val tvAdult=dialog.findViewById<TextView>(R.id.tv_adult)
            val tvNoOfAdult=dialog.findViewById<TextView>(R.id.tv_no_of_adult)
            val tvChild=dialog.findViewById<TextView>(R.id.tv_child)
            val tvNoOfChild=dialog.findViewById<TextView>(R.id.tv_no_of_childern)
            val tvInfant=dialog.findViewById<TextView>(R.id.tv_infant)
            val tvNoOfInfant=dialog.findViewById<TextView>(R.id.tv_no_of_infant)

            val formattedAdult = String.format("%02d", noOfAdult)
            val formattedChild = String.format("%02d", noOfChild)
            val formattedInfant = String.format("%02d", noOfInfant)

            tvAdult!!.text = "$formattedAdult Adults"
            tvNoOfAdult!!.text="$formattedAdult"
            tvChild!!.text =if (noOfChild<=1) "$formattedChild Child" else "$formattedChild Children"
            tvNoOfChild!!.text="$formattedChild"
            tvInfant!!.text = "$formattedInfant Infants"
            tvNoOfInfant!!.text = "$formattedInfant"

            btnAdultUp!!.setOnClickListener {
                noOfAdult += 1
                val formatted = String.format("%02d", noOfAdult)
                tvAdult!!.text = "$formatted Adults"
                tvNoOfAdult!!.text="$formatted"
            }
            btnAdultDown!!.setOnClickListener {
                noOfAdult -= 1
                if (noOfAdult<=1) noOfAdult=1
                val formatted = String.format("%02d", noOfAdult)
                tvAdult!!.text = "$formatted Adults"
                tvNoOfAdult!!.text="$formatted"
            }

            btnChildUp!!.setOnClickListener {
                noOfChild +=1
                val formatted = String.format("%02d", noOfChild)
                tvChild!!.text = if (noOfChild<=1) "$formatted Child" else "$formatted Children"
                tvNoOfChild!!.text="$formatted"
            }
            btnChildDown!!.setOnClickListener {
                noOfChild -=1
                if (noOfChild<=0)noOfChild=0
                val formatted = String.format("%02d", noOfChild)
                tvChild!!.text = if (noOfChild<=1) "$formatted Child" else "$formatted Children"
                tvNoOfChild!!.text="$formatted"
            }
            btnInfantUp!!.setOnClickListener {
                noOfInfant+=1
                val formatted = String.format("%02d", noOfInfant)
                tvInfant!!.text = "$formatted Infants"
                tvNoOfInfant!!.text = "$formatted"
            }
            btnInfantDown!!.setOnClickListener {
                noOfInfant-=1
                if (noOfInfant<=0)noOfInfant=0
                val formatted = String.format("%02d", noOfInfant)
                tvInfant!!.text = "$formatted Infants"
                tvNoOfInfant!!.text = "$formatted"
            }

            dialog.show()

            btnDone!!.setOnClickListener {
                binding.edAdult.setText("$noOfAdult Adults")
                if (noOfChild<=1)binding.edChild.setText("$noOfChild Child") else binding.edChild.setText("$noOfChild Children")
                binding.edInfant.setText("$noOfInfant Infants")
                dialog.dismiss()
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTimeAheadThreeMinutes():kotlin.String{
        val timestamp= SimpleDateFormat("HH:mm:ss")
        val time = Calendar.getInstance()
        time.add(Calendar.MINUTE, 3)
        val d  = timestamp.format(time.time)
        //Toast.makeText(requireContext(),d.toString(), Toast.LENGTH_LONG).show()
        return d.toString()
    }

    private fun validateFields():Boolean{
        if (origin==""){
            binding.edOrigin.error = getString(R.string.lbl_field_required)
            return false
        }
        if (destination==""){
            binding.edFinal.error = getString(R.string.lbl_field_required)
            return false
        }
        if (fromDate==""){
            binding.edFromDate.error = getString(R.string.lbl_field_required)
            return false
        }
        return true
    }

}