package com.trips.pk.ui.flight.book

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookingContainerBinding
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassengerType
import com.trips.pk.model.flight.book.PassportInfo
import com.trips.pk.ui.common.*
import com.trips.pk.ui.flight.book.newpck.AllUser
import com.trips.pk.ui.flight.book.newpck.FlightBookNewFragment

class FlightBookHostFragment: Fragment() {
    private lateinit var binding:FragmentFlightBookingContainerBinding
    private var currentIndex=0

    private var mPassenger:Passenger?=null
    private var mContactPerson:ContactPerson?=null

    private var userList = arrayListOf<AllUser>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFlightBookingContainerBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Passenger Info"


        var siz= mNoOfAdult + mNoOfChildern + mNoOfInfent
        mTotalPassenger.clear()
        for (i in 0 until siz){
            mTotalPassenger.add(PassengerType(1))//Here 1 in passengertype is useless.
            val contactPerson=ContactPerson()
            val passportInfo=PassportInfo()
            val passenger=Passenger(passportInfo=passportInfo)
         userList.add(AllUser(contactPerson,passenger,0))
        }


        binding.btnNext.setOnClickListener {
            isNextClicked.value=true
          //  currentIndex++
            //getPassengerInfo(R.anim.slide_in_right,R.anim.slide_out_left)
           // previewButton()
        }
        binding.btnPrev.setOnClickListener {
            currentIndex--
            if (currentIndex<0)currentIndex=0
            getPassengerInfo(R.anim.slide_out_right,R.anim.slide_in_left)
            previewButton()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassengerInfo( 0,0)
        previewButton()
    }

    private fun getPassengerInfo(animEnter: Int, animExit: Int) {
        val bundle = Bundle()
        bundle.putInt("index", currentIndex)
        if (userList.isNotEmpty()){
            if (currentIndex >= userList.size || currentIndex<0){
               //index not existed
            }else{
                if (userList[currentIndex]!=null){
                    bundle.putSerializable("user",userList[currentIndex])
                }
            }

        }

        val frag = FlightBookNewFragment()
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(animEnter, animExit)
        frag.arguments = bundle
        transaction?.replace(R.id.flight_booking_host, frag)
        transaction?.commit()
        frag.setListener(object : TempListener{
            override fun onTempClick(which: String,user:AllUser) {
                if (which=="true"){
                    try {
                        if (user.index==0){
                            mContactPerson=user.contactPerson
                        }
                        userList[currentIndex] = user
                        currentIndex++
                        if (currentIndex>userList.size-1){
                            Toast.makeText(requireContext(),"to submit",Toast.LENGTH_SHORT).show()
                        }else{
                            getPassengerInfo(R.anim.slide_in_right,R.anim.slide_out_left)
                            previewButton()
                        }
                    }catch (e:java.lang.Exception){
                        e.printStackTrace()
                    }


                }
            }

        })
    }

    private fun previewButton() {
        binding.btnPrev.visibility=if (currentIndex>=1) View.VISIBLE else View.INVISIBLE
      //  binding.btnNext.visibility = if (currentIndex < mTotalPassenger.size-1) View.VISIBLE else View.INVISIBLE
//        binding.btnContinue.visibility =
//            if (currentIndex == 3) View.VISIBLE else View.GONE
    }


}