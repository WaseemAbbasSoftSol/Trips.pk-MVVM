package com.trips.pk.ui.flight.book

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.razir.progressbutton.hideProgress
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookingContainerBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.book.*
import com.trips.pk.ui.common.*
import com.trips.pk.ui.flight.book.newpck.AllUser
import com.trips.pk.ui.flight.book.newpck.FlightBookNewFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightBookHostFragment: Fragment() {
    private lateinit var binding:FragmentFlightBookingContainerBinding
    private val mViewModel:FlightBookViewModel by viewModel()
    private var currentIndex=0

    private var mPassenger:Passenger?=null
    private var mContactPerson:ContactPerson?=null

    private var userList = arrayListOf<AllUser>()
    private var flightDetail:ItinerariesDetail?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFlightBookingContainerBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Passenger Info"
        flightDetail= sItinerariesDetail
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
            getPassengerInfo(R.anim.slide_out_right,R.anim.slide_in_left,getPassengerTypeCount(false))
            previewButton()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        getPassengerInfo( 0,0,0)
        previewButton()

        mViewModel.bookConfirmationMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == FLIGHT_BOOKED_SUCCESSFULLY){
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_flight_book_to_confirmation_fragment)
            }else{
                binding.btnContinue.hideProgress(R.string.lbl_continue_next)
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPassengerInfo(animEnter: Int, animExit: Int,count:Int) {
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
            @SuppressLint("SuspiciousIndentation")
            override fun onTempClick(which: String, user:AllUser) {
                if (which=="true"){
                    try {
                        if (user.index==0){
                            mContactPerson=user.contactPerson
                        }
                        if (currentIndex >= userList.size || currentIndex<0){
                           userList[mTotalPassenger.size-1] = user
                        }else{
                            userList[currentIndex] = user
                            currentIndex++
                        }
                        if (currentIndex>userList.size-1){
                            val contactPerson:ContactPerson=userList[0].contactPerson
                            val passengerList= arrayListOf<Passenger>()
                            for ((i,value)in userList.withIndex()){
                                passengerList.add(value.passenger)
                            }
                            val booker=FlightBooker(contactPerson.name,contactPerson.number,contactPerson.gender,contactPerson.email,contactPerson.zipCode,
                                contactPerson.address,contactPerson.countryId,contactPerson.cityId,passengerList,flightDetail!!)
                              mViewModel.bookFlight(booker)
                            Toast.makeText(requireContext(),"to submit",Toast.LENGTH_SHORT).show()
                        }else{
                            getPassengerInfo(R.anim.slide_in_right,R.anim.slide_out_left,getPassengerTypeCount(true))
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
    private fun getPassengerTypeCount(isNext:Boolean):Int{
        var count = 0

        var adult= mNoOfAdult
        var child= mNoOfChildern
        var infent= mNoOfInfent

        if (currentIndex<adult) {
            if (isNext )whichAdult+=1 else whichAdult -=1
            count= whichAdult
        }
        else if(currentIndex>=adult && currentIndex < adult+child){
            if (isNext ) whichChild+=1 else whichChild -=1
            count= whichChild
        }
        else {
            if (isNext ) whichInfant+=1 else whichInfant -=1
            count= whichInfant
        }

        return count
    }

}