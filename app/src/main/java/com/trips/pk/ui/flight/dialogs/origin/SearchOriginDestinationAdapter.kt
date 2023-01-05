package com.trips.pk.ui.flight.dialogs.origin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.Airport
import com.trips.pk.ui.common.OnListItemClickListener
import java.util.*

class SearchOriginDestinationAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<SearchOriginDestinationAdapter.ItemRecyclerViewHolder>() {
    private var list = arrayListOf<Airport>()
    private var filteredList= arrayListOf<Airport>()
    private var listener:OnListItemClickListener<Airport>?=null
    constructor(contexts: Context,
                list: List<Airport>,
                listener: OnListItemClickListener<Airport>):this(contexts){
        this.list= list as ArrayList<Airport>
        filteredList.addAll(list)
        this.listener=listener
    }

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView =itemView.findViewById(R.id.tv_city)
        val country: TextView =itemView.findViewById(R.id.tv_country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_search_origin,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
       val airport = list[position]
        holder.name.text= airport.airportName
        holder.country.text=airport.airportCode
        holder.itemView.setOnClickListener {
            listener!!.onItemClick(airport, position)
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun filter(searchedText: String) {
        var text = searchedText.trim()
        list.clear()
        if (text.isEmpty()) {
            list.addAll(filteredList)
        } else {
            text = text.lowercase(Locale.getDefault())
            for (item in filteredList) {
                if (item.airportName.toLowerCase().contains(text) ||
                            item.airportCode.toLowerCase().contains(text)
                ) {
                    list.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
}