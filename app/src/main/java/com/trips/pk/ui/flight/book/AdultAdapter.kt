package com.trips.pk.ui.flight.book


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.trips.pk.R
import com.trips.pk.model.flight.book.Adult
import com.trips.pk.ui.common.*
import com.trips.pk.utils.date
import com.trips.pk.utils.mCalendar
import java.util.*

const val VIEW_TYPE_ADULT=1
const val VIEW_TYPE_CHILD=2
const val VIEW_TYPE_INFANT=3

class AdultAdapter(
    val context: Context,
    var listener:AdultTextGetter,
    var layoutManager:LinearLayoutManager?=null
) :
    RecyclerView.Adapter<AdultAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prefix=itemView.findViewById<MaterialAutoCompleteTextView>(R.id.sp_prefix)
        val firstName=itemView.findViewById<TextInputEditText>(R.id.ed_first_name)
        val middleName=itemView.findViewById<TextInputEditText>(R.id.ed_middle_name)
        val lastName=itemView.findViewById<TextInputEditText>(R.id.ed_last_name)
        val dob=itemView.findViewById<AutoCompleteTextView>(R.id.tv_dob)
        val edContact=itemView.findViewById<TextInputEditText>(R.id.ed_contact)
        val edEmail=itemView.findViewById<TextInputEditText>(R.id.ed_email)
        val edAddress=itemView.findViewById<TextInputEditText>(R.id.ed_address)
        val edZipCode=itemView.findViewById<TextInputEditText>(R.id.ed_zipcode)
        val edCity=itemView.findViewById<TextInputEditText>(R.id.ed_city)
        val edCountry=itemView.findViewById<TextInputEditText>(R.id.ed_country)
        val edPassportNo=itemView.findViewById<TextInputEditText>(R.id.ed_passport_no)
        val edPassportExpireDate=itemView.findViewById<AutoCompleteTextView>(R.id.tv_expire_date)
        val edPassportNatCountry=itemView.findViewById<TextInputEditText>(R.id.ed_passport_nat_country)

        val llContact=itemView.findViewById<LinearLayout>(R.id.ll_contact)
        val llemail=itemView.findViewById<LinearLayout>(R.id.ll_email)
        val lladdress=itemView.findViewById<LinearLayout>(R.id.ll_address)
        val llzipcode=itemView.findViewById<LinearLayout>(R.id.ll_zipcode)
        val llcity=itemView.findViewById<LinearLayout>(R.id.ll_city)
        val llcontry=itemView.findViewById<LinearLayout>(R.id.ll_country)

        val etFirstName=itemView.findViewById<TextInputLayout>(R.id.et_first_name)
        val etMiddleName=itemView.findViewById<TextInputLayout>(R.id.et_middle_name)
        val etLastName=itemView.findViewById<TextInputLayout>(R.id.et_last_name)
        val etDob=itemView.findViewById<TextInputLayout>(R.id.et_dob)
        val etContact=itemView.findViewById<TextInputLayout>(R.id.et_contact)
        val etEmail=itemView.findViewById<TextInputLayout>(R.id.et_email)
        val etAddress=itemView.findViewById<TextInputLayout>(R.id.et_address)
        val etZipCode=itemView.findViewById<TextInputLayout>(R.id.et_zipcode)
        val etCity=itemView.findViewById<TextInputLayout>(R.id.et_city)
        val etCountry=itemView.findViewById<TextInputLayout>(R.id.et_country)
        val etPassportNo=itemView.findViewById<TextInputLayout>(R.id.et_passport_no)
        val etPassportExpireDate=itemView.findViewById<TextInputLayout>(R.id.et_passport_expire_date)
        val etPassportNatCountry=itemView.findViewById<TextInputLayout>(R.id.et_passport_nat_country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.temp,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

        if (position==0){
            holder.llContact.visibility=View.VISIBLE
            holder.llemail.visibility=View.VISIBLE
            holder.lladdress.visibility=View.VISIBLE
            holder.llzipcode.visibility=View.VISIBLE
            holder.llcity.visibility=View.VISIBLE
            holder.llcontry.visibility=View.VISIBLE
        }else {
            holder.llContact.visibility=View.GONE
            holder.llemail.visibility=View.GONE
            holder.lladdress.visibility=View.GONE
            holder.llzipcode.visibility=View.GONE
            holder.llcity.visibility=View.GONE
            holder.llcontry.visibility=View.GONE
        }

        setSpinner(holder.prefix,prefixList)
        holder.dob.setOnClickListener {
            DatePickerDialog(
                context,
                date,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
         sharedDob=holder.dob
        }

        holder.edPassportExpireDate.setOnClickListener {
            DatePickerDialog(
                context,
                date,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            sharedExpireDate=holder.edPassportExpireDate
        }

        isButtonClick.observe(holder.itemView.context as LifecycleOwner) {
            if (it) {
                    val prefix = holder.prefix.text.toString().trim()
                    val firstName = holder.firstName.text.toString().trim()
                    val middleName = holder.middleName.text.toString().trim()
                    val lastName = holder.lastName.text.toString().trim()
                val dob=holder.dob.text.toString().trim()
                val contact=holder.edContact.text.toString().toString()
                val email=holder.edEmail.text.toString().trim()
                val address=holder.edAddress.text.toString().trim()
                val zipCode=holder.edZipCode.text.toString().trim()
                val city=holder.edCity.text.toString().trim()
                val country=holder.edCountry.text.toString().toString()
                val passportNo=holder.edPassportNo.text.toString().trim()
                val passExDate=holder.edPassportExpireDate.text.toString().trim()
                val passNat=holder.edPassportNatCountry.text.toString().trim()

                    val adult=Adult(prefix, firstName, middleName, lastName)
                var viewType: Int = if (position<2) VIEW_TYPE_ADULT else if (position <5) VIEW_TYPE_CHILD else VIEW_TYPE_INFANT

                var isValid=true
                for (i in 0..8){
                    if (firstName.isNullOrEmpty() && i==position){
                        holder.etFirstName.error = context.getString(R.string.lbl_field_required)
                        isValid=false
                          layoutManager!!.scrollToPosition(position)
                    }else if (middleName.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etMiddleName.error = context.getString(R.string.lbl_field_required)
                        layoutManager!!.scrollToPosition(position)
                    }
                    else if (lastName.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etLastName.error = context.getString(R.string.lbl_field_required)
                          layoutManager!!.scrollToPosition(position)
                    }
                    else if (dob.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etDob.error = context.getString(R.string.lbl_field_required)
                        layoutManager!!.scrollToPosition(position)
                    }
                    else if (contact.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etContact.error = context.getString(R.string.lbl_field_required)
                        layoutManager!!.scrollToPosition(position)
                    }
                    else if (email.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etEmail.error = context.getString(R.string.lbl_field_required)
                        layoutManager!!.scrollToPosition(position)
                    }
                    else if (address.isNullOrEmpty() && i==position){
                        isValid=false
                        holder.etAddress.error = context.getString(R.string.lbl_field_required)
                        layoutManager!!.scrollToPosition(position)
                    }
                    else{
                        isValid=true
                        listener.onTextChanged(adult, position, viewType, position==8)
                    }
                }
               if (!isValid) Toast.makeText(context,"Please fill all fields",Toast.LENGTH_SHORT).show()


            }
        }


    }

    override fun getItemCount(): Int {
      return 9
    }

    override fun getItemViewType(position: Int): Int {
        return if (position<2) VIEW_TYPE_ADULT
        else if (position < 5) VIEW_TYPE_CHILD
        else VIEW_TYPE_INFANT
    }

    @SuppressLint("SuspiciousIndentation")
    private fun validateFields(editText: TextInputEditText, tv:TextInputLayout):Boolean{
        if (editText.text.toString().trim().isNullOrEmpty()){
         tv.error = "This field is required"
            return false
        }
        return true
    }

    interface AdultTextGetter{
        fun onTextChanged(adult: Adult, position: Int, viewType:Int, isLast:Boolean)
    }


    fun setSpinner(view:AutoCompleteTextView,items:List<Prefix>){
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, items)
        view.setAdapter(adapter)
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    }

}