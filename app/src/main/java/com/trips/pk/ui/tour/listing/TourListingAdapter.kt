package com.trips.pk.ui.tour.listing

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.ui.common.DummyClickListener

class TourListingAdapter(
    val context: Context,
    val size:Int,
    val listener:DummyClickListener
) :
    RecyclerView.Adapter<TourListingAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_tour_listing,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
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