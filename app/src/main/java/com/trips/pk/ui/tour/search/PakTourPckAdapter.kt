package com.trips.pk.ui.tour.search

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
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.OnListItemClickListener
import java.text.NumberFormat

class PakTourPckAdapter(
    val context: Context,
    val size:Int,
    val listener:DummyClickListener,
    val list : CountriesWithCities? = null
) :
    RecyclerView.Adapter<PakTourPckAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val city = itemView.findViewById<TextView>(R.id.tv_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_pak_tour_pkg,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        if (list!=null){
            val obj = list.cities[position]
            holder.city.text=obj.name
        }
        try {
            holder.itemView.setOnClickListener {
                listener.onDummyClick()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getItemCount(): Int {
        return size
    }

}