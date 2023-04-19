package com.trips.pk.ui.flight.listing

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.OnListItemClickListener
import java.text.NumberFormat


class AirlinesAndStopsAdapter(
    val context: Context,
    val flightList:ArrayList<ItinerariesDetail>,
    val listener:OnListItemClickListener<ItinerariesDetail>?=null
) :
    RecyclerView.Adapter<AirlinesAndStopsAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val airlineLogo=itemView.findViewById<ImageView>(R.id.iv_logo)
        val totalMoney=itemView.findViewById<TextView>(R.id.tv_money)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_airlines_and_stops,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        try {
            if (position==0) setMargins(holder.itemView,25,0,0,0)
            val flight=flightList[position]
            val fare=flight.pricingInformation.fare

            Glide.with(context).load(flight.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineLogo).into(holder.airlineLogo)


            val c=fare.fares.currency
            val money=String.format("%.0f",fare.fares.totalFare)
            val m=Integer.parseInt(money)
            val formattedMoney= NumberFormat.getInstance().format(m);
            holder.totalMoney.text = "$c $formattedMoney"
            holder.itemView.setOnClickListener {
                listener!!.onItemClick(flight,position)
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getItemCount(): Int {
        return flightList.size
    }
    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

    fun updateList(list: List<ItinerariesDetail>){
        flightList.clear()
        flightList.addAll(list)
        notifyDataSetChanged()
    }
}