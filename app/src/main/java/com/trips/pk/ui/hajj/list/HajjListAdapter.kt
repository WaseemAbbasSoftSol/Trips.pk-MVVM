package com.trips.pk.ui.hajj.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R

class HajjListAdapter (
        private val context:Context,
        private val listener: HajjViewAllClickListener
        ):RecyclerView.Adapter<HajjListAdapter.ItemRecyclerViewHolder>() {

        class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
                return ItemRecyclerViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_hajj_inner_new, parent, false)
                )
        }

        override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
                holder.itemView.setOnClickListener { listener.onViewAllClick(position) }
        }

        override fun getItemCount(): Int {
                return 14
        }

        interface HajjViewAllClickListener {
                fun onViewAllClick(position: Int)
        }
}