package com.proyectodev.yummydroid.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Command
import com.proyectodev.yummydroid.model.Table
import kotlinx.android.synthetic.main.item_command.view.*

class CommandsListRecyclerViewAdapter: RecyclerView.Adapter<CommandsListRecyclerViewAdapter.CommandViewHolder>{

    private val table: Table
    private val itemClickListener: ((Command, Int) -> Unit)?

    constructor(table: Table, itemClickListener: ((Command, Int) -> Unit)): super() {
        this.table = table
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_command, parent, false)

        return CommandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return table.getCommands().size
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val item = table.getCommand(position)

        holder.command = item

        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderPair));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderOdd));
        }
    }

    inner class CommandViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var command: Command? = null
            set(value) {
                value?.let {
                    itemView.command_dish_name_text.text = it.dish.name
                    itemView.command_dish_price_text.text = "${it.dish.price} €"
                    itemView.command_variants_text.text = it.variants
                }
                field = value
            }

        init {
            itemView.command_remove_btn.setOnClickListener {
                command?.let {
                    itemClickListener?.invoke(command as Command, adapterPosition)
                }

            }

        }


    }
}