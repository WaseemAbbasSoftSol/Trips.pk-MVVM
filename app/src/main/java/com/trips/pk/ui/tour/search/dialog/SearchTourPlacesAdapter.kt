package com.trips.pk.ui.tour.search.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.flight.Airport
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.CountryAndCityTogether
import com.trips.pk.ui.common.OnListItemClickListener
import java.util.*

class SearchTourPlacesAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<SearchTourPlacesAdapter.ItemRecyclerViewHolder>() {
    private var list = arrayListOf<CountryAndCityTogether>()
    private var filteredList= arrayListOf<CountryAndCityTogether>()
    private var listener:OnListItemClickListener<CountryAndCityTogether>?=null
    constructor(contexts: Context,
                list: List<CountryAndCityTogether>,
                listener: OnListItemClickListener<CountryAndCityTogether>):this(contexts){
        this.list= list as ArrayList<CountryAndCityTogether>
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
        val places = list[position]
        holder.name.text= places.name
        holder.country.visibility=View.GONE
        holder.itemView.setOnClickListener {
            listener!!.onItemClick(places, position)
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
                if (item.name.toLowerCase().contains(text)
                ) {
                    list.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
}