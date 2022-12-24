package com.trips.pk.ui.flight.listing

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail


class AirlinesAndStopsAdapter(
    val context: Context,
    val flightList:ArrayList<ItinerariesDetail>
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
        val flight=flightList[position]
        Glide.with(context).load(flight.legs[0].beggageInformation.airlineLogo).into(holder.airlineLogo)
    }
    override fun getItemCount(): Int {
        return flightList.size
    }

}