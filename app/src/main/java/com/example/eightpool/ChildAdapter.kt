package com.example.eightpool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildAdapter(val context: Context,val  innerItems: List<Item>) : RecyclerView.Adapter<ChildAdapter.InnerViewHolder>() {

    interface onItemClickedListeneM{
        fun onItemCLick(item:Item){}
    }
     var itemClickListener:onItemClickedListeneM?=null

    fun setOnItemClickListener(listener:ChildAdapter.onItemClickedListeneM){
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item_drawable, parent, false)
        return InnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val innerItem = innerItems[position]
        holder.bind(innerItem)

    }

    override fun getItemCount(): Int {
        return innerItems.size
    }

    inner class InnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtFoodName: TextView = itemView.findViewById(R.id.txtFoodName)
        private val txtIngredients: TextView = itemView.findViewById(R.id.txtIngredients)
        private val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        private val imgFoodItem: ImageView = itemView.findViewById(R.id.imgFoodItem)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val clickedItem = innerItems[position]
                    itemClickListener?.onItemCLick(clickedItem)
                }
            }
        }
        fun bind(innerItem: Item) {
            // Bind inner item data to views
            txtFoodName.text = innerItem.foodName
            txtIngredients.text = innerItem.ingredients
            txtPrice.text = innerItem.price
            imgFoodItem.setImageResource(innerItem.imageResId)
        }
    }


}
