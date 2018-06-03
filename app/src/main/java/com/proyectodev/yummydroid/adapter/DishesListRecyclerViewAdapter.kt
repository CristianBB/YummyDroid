package com.proyectodev.yummydroid.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Dish
import com.proyectodev.yummydroid.model.Dishes
import kotlinx.android.synthetic.main.item_dish.view.*

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

        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderPair));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderOdd));
        }
    }

    inner class DishViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var dish: Dish? = null
            set(value) {
                value?.let {
                    itemView.item_dish_image.setImageResource(it.image)
                    itemView.item_dish_name_text.text = it.name
                    itemView.item_price_text.text = "${it.price} €"


                    it.allergens?.let {
                        it.forEach {
                            val imageView = ImageView(itemView.context)
                            imageView.setImageResource(it.image)

                            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                            layoutParams.setMargins(0,0,10,0)
                            imageView.layoutParams = layoutParams

                            itemView.item_allergens_layout.addView(imageView)
                        }
                    }

                }

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