package com.trips.pk.ui.flight.book

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.databinding.FragmentFlightBookBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassengerType
import com.trips.pk.ui.common.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.List


class FlightBookFragment:Fragment(), AdultAdapter.AdultTextGetter {
    private lateinit var binding:FragmentFlightBookBinding
    private val mViewModel:FlightBookViewModel by viewModel()
    private var booker= arrayListOf<Passenger>()
    private var adapter:AdultAdapter?=null
    private var flightDetail:ItinerariesDetail?=null


    private val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args=FlightBookFragmentArgs.fromBundle(it!!)
            flightDetail=args.detail
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this


        binding.btnContinue.setOnClickListener {
            mPassengerList.clear()
            mContactPeron.clear()
            mIsValid=true
            isButtonClick.value=true
        }
    /*    val adultView=View.inflate(requireContext(), R.layout.adult_layout, null)
        binding.clAdult.addView(adultView)

        val childView=View.inflate(requireContext(), R.layout.child_layout, null)
        binding.clChild.addView(childView)*/

        var siz= mNoOfAdult+ mNoOfChildern+ mNoOfInfent
        for (i in 0..siz-1){
            mTotalPassenger.add(PassengerType(1))
        }

        val layoutManager1 = LinearLayoutManager(requireContext())
        binding.rvAdult.layoutManager = layoutManager1
        adapter=AdultAdapter(requireContext(), this, layoutManager1)
        binding.rvAdult.setHasFixedSize(true)
        binding.rvAdult.isNestedScrollingEnabled=false
        binding.rvAdult.adapter = adapter

        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateDobLabel()
            }

    /*    binding.tvDob.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    override fun onTextChanged(contactPerson: List<ContactPerson>,passenger: List<Passenger>, position: Int, isLast:Boolean) {
        val contact=contactPerson[0]
        val booker=FlightBooker(contact.name,contact.number,contact.gender,contact.email,passenger,flightDetail!!)
        mViewModel.bookFlight(booker)

    }



    private fun updateDobLabel() {
//        val myFormat = "MM/dd/yy"
//        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
//        binding.tvDob.setText(dateFormat.format(myCalendar.time))

        val myFormat = "dd MMM, yyyy"
        val serverFormat="yyyy-MM-dd"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val dateFormatForServer = SimpleDateFormat(serverFormat, Locale.US)
       // dob=dateFormatForServer.format(myCalendar.time)
       // binding.tvDob.setText(dateFormat.format(myCalendar.time))
    }


}