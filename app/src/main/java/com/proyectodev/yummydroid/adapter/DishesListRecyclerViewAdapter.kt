package com.proyectodev.yummydroid.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Dish
import com.proyectodev.yummydroid.model.Dishes

class DishesListRecyclerViewAdapter: RecyclerView.Adapter<DishesListRecyclerViewAdapter.DishViewHolder>{

    private val itemClickListener: ((Dish, Int) -> Unit)?

    constructor(itemClickListener: ((Dish, Int) -> Unit)): super() {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)

        return DishViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Dishes.count
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val item = Dishes.getDish(position)

        holder.dish = item
    }

    inner class DishViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var dish: Dish? = null
            set(value) {
                itemView.findViewById<TextView>(R.id.dish_name_text).text = value?.name
                field = value
            }

        init {
            itemView.setOnClickListener {
                dish?.let {
                    itemClickListener?.invoke(dish as Dish, adapterPosition)
                }

            }

        }


    }
}