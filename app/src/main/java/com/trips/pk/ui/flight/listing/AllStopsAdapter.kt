package com.trips.pk.ui.flight.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.Legs
import kotlin.collections.ArrayList


class AllStopsAdapter(
    val context: Context,
    val flightList:ArrayList<ItinerariesDetail>,
    private val listener:FlightListClickListener,
): RecyclerView.Adapter<AllStopsAdapter.ItemRecyclerViewHolder>(),StopsInnerAdapter.InnerListListener {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rv: RecyclerView =itemView.findViewById(R.id.rv_item_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_main_rv_list,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        val flight=flightList[position]
        setInnerList(holder.rv,flight.legs[0], flight)
    }
    override fun getItemCount(): Int {
        return flightList!!.size
    }
    interface FlightListClickListener{
        fun onListClick(flight: ItinerariesDetail, position: Int)
    }

    private fun setInnerList(recyclerView: RecyclerView, leg:Legs, flight: ItinerariesDetail) {
        val itemRecyclerAdapter = StopsInnerAdapter(context,leg, flight,this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemRecyclerAdapter
    }

    override fun onListClick(flight: ItinerariesDetail, position: Int) {
        TODO("Not yet implemented")
    }
}