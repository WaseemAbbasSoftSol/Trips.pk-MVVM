package com.trips.pk.ui.flight.book.newpck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookNewBinding
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassportInfo
import com.trips.pk.ui.common.*


class FlightBookNewFragment:Fragment() {
    private lateinit var binding:FragmentFlightBookNewBinding

    private var contactPerson:ContactPerson?=null
    private var user:AllUser?=null
    private var index = 0

    private var listener:TempListener?=null

    var gender = ""
    var firstName=""
    var middleName =""
    var lastName=""
    var dob=""
    var contact = ""
    var email =""
    var address=""
    var zip=""
    var city=""
    var country=""
    var pasNo="0"
    var pasExDate=""
    var pasCountry=""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookNewBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        val bundle = this.arguments
        index=bundle!!.getInt("index")
        user=bundle!!.getSerializable("user") as? AllUser
       visibilityElements()
        user.let {
            if (it!=null){
                if (it.index ==index){
                    val prefix=if (it.passenger.gender == "Male") "Mr" else "Mrs"
                    binding.spPrefix.setText(prefix)
                    binding.edFirstName.setText(it.passenger.firstName)
                    binding.edMiddleName.setText(it.passenger.middleName)
                    binding.edLastName.setText(it.passenger.lastName)
                    binding.tvDob.setText(it.passenger.dob)
                    binding.edPassportNo.setText(it.passenger.passportInfo!!.passportNumber)
                    binding.tvExpireDate.setText(it.passenger.passportInfo!!.expirtyDate)
                }

            }
        }

        var adult= mNoOfAdult
        var child= mNoOfChildern
        var infent= mNoOfInfent
        var type=""
        if (index<adult) type="adult"
        else if(index>=adult && index < adult+child) type="child"
        else type = "infant"

        if (index==0)binding.tvEnterInfo.text = "Enter Info about the Traveler\nContact Person"
       else binding.tvEnterInfo.text = "Enter Info about the Traveler\n$type ${getPassengerTypeCount()}"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNextClicked.observe(viewLifecycleOwner, Observer {
            if (it){


                gender=binding.spPrefix.text.toString().trim()
                firstName=binding.edFirstName.text.toString().trim()
                middleName=binding.edMiddleName.text.toString().trim()
                lastName=binding.edLastName.text.toString().trim()
                dob=binding.tvDob.text.toString().trim()
                contact=binding.edContact.text.toString().trim()
                email=binding.edEmail.text.toString().trim()
                address=binding.edAddress.text.toString().trim()
                zip=binding.edZipcode.text.toString().trim()
                city=binding.edCity.text.toString().trim()
                country=binding.edCountry.text.toString().trim()
                pasNo=binding.edPassportNo.text.toString().trim()
                pasExDate=binding.tvExpireDate.text.toString().trim()
                pasCountry=binding.edPassportNatCountry.text.toString().trim()

                if(firstName.isNullOrEmpty()){
                    isNextClicked.value=false
                    binding.etFirstName.error = getString(R.string.lbl_field_required)
                } else if (lastName.isNullOrEmpty()){
                    isNextClicked.value=false
                    binding.etLastName.error = getString(R.string.lbl_field_required)
                }
//                else if (dob.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etDob.error = getString(R.string.lbl_field_required)
//                }
//                else if (contact.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etContact.error = getString(R.string.lbl_field_required)
//                }
//                else if (email.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etEmail.error = getString(R.string.lbl_field_required)
//                }
//                else if (address.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etAddress.error = getString(R.string.lbl_field_required)
//                }
//                else if (zip.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etZipcode.error = getString(R.string.lbl_field_required)
//                }
//                else if (city.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etCity.error = getString(R.string.lbl_field_required)
//                }
//                else if (country.isNullOrEmpty() && index==0){
//                    isNextClicked.value=false
//                    binding.etCountry.error = getString(R.string.lbl_field_required)
//                }
//                else if (pasNo==0){
//                    isNextClicked.value=false
//                    binding.etPassportNo.error = getString(R.string.lbl_field_required)
//                }
//                else if (pasExDate.isNullOrEmpty()){
//                    isNextClicked.value=false
//                    binding.etPassportExpireDate.error = getString(R.string.lbl_field_required)
//                }
//                else if (pasCountry.isNullOrEmpty()){
//                    isNextClicked.value=false
//                    binding.etPassportNatCountry.error = getString(R.string.lbl_field_required)
//                }
                else{
                    val fullName="$gender $firstName $middleName $lastName"
                    val contactPerson=ContactPerson(fullName,contact,gender,email,zip,address,country,city)
                    val passportInfo=PassportInfo(0,pasExDate,pasCountry)
                    val passenger=Passenger(firstName,middleName,lastName,gender,dob,"",passportInfo)
                    user=AllUser(contactPerson,passenger,index)
                    isNextClicked.value=false
                    listener!!.onTempClick("true",user!!)
                }
            }

        })
    }
    fun setListener(listener: TempListener){
        this.listener=listener
    }

    private fun visibilityElements(){
        if (index==0){
            binding.llDob.visibility=View.VISIBLE
            binding.llContact.visibility=View.VISIBLE
            binding.llEmail.visibility=View.VISIBLE
            binding.llAddress.visibility=View.VISIBLE
            binding.llZipcode.visibility=View.VISIBLE
            binding.llCity.visibility=View.VISIBLE
            binding.llCountry.visibility=View.VISIBLE
        }else{
            binding.llDob.visibility=View.GONE
            binding.llContact.visibility=View.GONE
            binding.llEmail.visibility=View.GONE
            binding.llAddress.visibility=View.GONE
            binding.llZipcode.visibility=View.GONE
            binding.llCity.visibility=View.GONE
            binding.llCountry.visibility=View.GONE
        }
    }

    private fun getPassengerTypeCount():String{
        var count = ""

        var adult= mNoOfAdult
        var child= mNoOfChildern
        var infent= mNoOfInfent

        if (index<adult) {
            whichAdult+=1
            count= whichAdult.toString()
        }
        else if(index>=adult && index < adult+child){
           whichChild+=1
            count= whichChild.toString()
        }
        else {
            whichInfant+=1
            count= whichInfant.toString()
        }

        return count
    }
}