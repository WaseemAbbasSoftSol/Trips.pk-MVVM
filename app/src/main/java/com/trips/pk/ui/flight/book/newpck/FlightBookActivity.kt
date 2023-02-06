package com.trips.pk.ui.flight.book.newpck

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.ActivityFlightBookBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassengerType
import com.trips.pk.ui.common.*
import com.trips.pk.utils.makeProgressOnButton

class FlightBookActivity : AppCompatActivity(),FlightBookAdapter.AdultTextGetter{
    private lateinit var binding:ActivityFlightBookBinding

   // private val mViewModel: FlightBookViewModel by viewModel()
    private var booker= arrayListOf<Passenger>()
    private var adapter: FlightBookAdapter?=null
    private var flightDetail: ItinerariesDetail?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlightBookBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        setContentView(binding.root)

   Toast.makeText(this,"entered in acitivyt",Toast.LENGTH_SHORT).show()

        binding.btnContinue.setOnClickListener {
            mPassengerList.clear()
            mContactPeron.clear()
            mIsValid =true
            isButtonClick.value=true


        }

        var siz= mNoOfAdult + mNoOfChildern + mNoOfInfent
        mTotalPassenger.clear()
        for (i in 0..siz-1){
            mTotalPassenger.add(PassengerType(1))//Here 1 in passengertype is useless.
        }

        val layoutManager1 = LinearLayoutManager(applicationContext)
        binding.rvBook.layoutManager = layoutManager1
        adapter= FlightBookAdapter(applicationContext,this)
        binding.rvBook.setHasFixedSize(true)
        binding.rvBook.isNestedScrollingEnabled=false
        binding.rvBook.adapter = adapter

//        binding.viewModel=mViewModel
//        mViewModel.bookConfirmationMessage.observe(this, androidx.lifecycle.Observer {
//            if (it == FLIGHT_BOOKED_SUCCESSFULLY){
//                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//              //  findNavController().navigate(R.id.action_flight_book_to_confirmation_fragment)
//            }else{
//                binding.btnContinue.hideProgress(R.string.lbl_continue_next)
//                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onTextChanged(
        contactPerson: List<ContactPerson>,
        passenger: List<Passenger>,
        position: Int,
        isLast: Boolean
    ) {
        val contact=contactPerson[0]
       // val booker= FlightBooker(contact.name,contact.number,contact.gender,contact.email,contact.zipCode,contact.address,contact.countryId,contact.cityId
        //    ,passenger,flightDetail!!)
        binding.btnContinue.makeProgressOnButton(R.string.lbl_wait)
      //  mViewModel.bookFlight(booker)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "finish ", Toast.LENGTH_SHORT).show()
        finish()
    }
}