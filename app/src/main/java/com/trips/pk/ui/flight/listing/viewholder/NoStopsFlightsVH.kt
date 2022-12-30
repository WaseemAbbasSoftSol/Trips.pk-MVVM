package com.trips.pk.ui.flight.listing.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import java.text.SimpleDateFormat
import java.util.*

class NoStopsFlightsVH(view: View, private var context: Context): RecyclerView.ViewHolder(view) {

    val oLogo=itemView.findViewById<ImageView>(R.id.iv_flight_origin)
    val oCarrier=itemView.findViewById<TextView>(R.id.tv_flight_origin)
    val oFlightCode=itemView.findViewById<TextView>(R.id.tv_flight_tc)
    val oTime=itemView.findViewById<TextView>(R.id.tv_flight_origin_time)
    val dTime=itemView.findViewById<TextView>(R.id.tv_flight_final_time)
    val oDate=itemView.findViewById<TextView>(R.id.tv_flight_origin_date)
    val dDate=itemView.findViewById<TextView>(R.id.tv_flight_final_date)
    val weight=itemView.findViewById<TextView>(R.id.tv_weight)
    val oTotalTime=itemView.findViewById<TextView>(R.id.tv_total_time)

    val money=itemView.findViewById<TextView>(R.id.tv_money)

    fun bindData(flight: ItinerariesDetail, holder: NoStopsFlightsVH){
        val firstLeg=flight.legs[0]
      //  Glide.with(context).load(firstLeg.beggageInformation.airlineLogo).into(holder.oLogo)

       // holder.oCarrier.text = firstLeg.beggageInformation.airlineName
        holder.oTime.text="${changeTimeFormat(firstLeg.schedules[0].departure.time)} ${firstLeg.schedules[0].departure.airport}"
        holder.dTime.text="${changeTimeFormat(firstLeg.schedules[0].arrival.time)} ${firstLeg.schedules[0].arrival.airport}"

      //  holder.oFlightCode.text=firstLeg.beggageInformation.airlineCode

        holder.oDate.text =changeDateFormat(firstLeg.schedules[0].departure.date)
        holder.dDate.text = changeDateFormat(firstLeg.schedules[0].arrival.date)
     //   holder.weight.text=firstLeg.beggageInformation.allowance
        holder.oTotalTime.text="${firstLeg.schedules[0].elapsedTime} hrs"
//
//        val c=flight.pricingInformation.currency.toString()
//        val money=String.format("%.2f",flight.pricingInformation.totalFare.toDouble())
//        holder.money.text = "$c $money"
    }

    private fun changeTimeFormat(time:String):String {
        var t= ""
        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val date: Date = parseFormat.parse(time)
            t = displayFormat.format(date)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return t
    }

    private fun changeDateFormat(dayDate:String):String{
        var actualDate=""
        try {
            val day=dayDate.substring(0,dayDate.indexOf(','))
            var date=dayDate.substring(dayDate.indexOf(',') + 1)

            /*val parser: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val formater: DateFormat = SimpleDateFormat("dd-MM-yy")
            date = date.drop(0)
            val convertedDate: Date = parser.parse(date)
            val da = formater.format(convertedDate)*/

            val d = when(day){
                "Sunday"-> "Sun"
                "Monday"-> "Mon"
                "Tuesday"-> "Tue"
                "Wednesday"-> "Wed"
                "Thursday"-> "Thu"
                "Friday"-> "Fri"
                "Saturday"-> "Sat"
                else -> ""
            }
            actualDate = "$d.$date"

        }catch (e:Exception){
            e.printStackTrace()
        }
        return actualDate
    }
}