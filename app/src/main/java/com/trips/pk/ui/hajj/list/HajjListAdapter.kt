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

                val rv: RecyclerView =itemView.findViewById(R.id.rv_hajj_inner)
                val tvViewAll: TextView = itemView.findViewById(R.id.tv_view_all)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
                return ItemRecyclerViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_hajj, parent, false)
                )
        }

        override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

                setInnerAdapter(holder.rv, context)
                holder.tvViewAll.setOnClickListener { listener.onViewAllClick(position) }
        }

        override fun getItemCount(): Int {
                return 5
        }

        private fun setInnerAdapter(rv:RecyclerView, ctx: Context){
                val adapter=HajjInnerAdapter(ctx)
                val layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
                rv.layoutManager=layoutManager
                rv.adapter=adapter
        }
        interface HajjViewAllClickListener {
                fun onViewAllClick(position: Int)
        }
}