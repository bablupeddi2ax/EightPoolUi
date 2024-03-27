package com.example.eightpool

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentAdapter(private val context: Context, private val outerItems: List<FoodItemType>) :
    RecyclerView.Adapter<ParentAdapter.OuterViewHolder>() {
     var innerAdapter:ChildAdapter? = ChildAdapter(context,listOf())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item_type, parent, false)
        return OuterViewHolder(view)
    }

    override fun onBindViewHolder(holder: OuterViewHolder, position: Int) {
        val outerItem = outerItems[position]
        holder.bind(outerItem)
    }

    override fun getItemCount(): Int {
        return outerItems.size
    }

    inner class OuterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerViewInner: RecyclerView = itemView.findViewById(R.id.recyclerViewFoodItems)

        fun bind(outerItem: FoodItemType) {
            // Initialize and set up inner RecyclerView adapter
            Log.i("insideparentadapter",outerItem.toString())
             innerAdapter = ChildAdapter(context, outerItem.foodItems)
            recyclerViewInner.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = innerAdapter
            }
        }
    }
}
