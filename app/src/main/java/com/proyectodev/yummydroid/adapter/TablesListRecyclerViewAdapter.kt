package com.proyectodev.yummydroid.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Table

class TablesListRecyclerViewAdapter: RecyclerView.Adapter<TablesListRecyclerViewAdapter.TableViewHolder>{

    private val items = mutableListOf<Table>()
    private val itemClickListener: ((Table, Int) -> Unit)?

    constructor(): super() {
        itemClickListener = null
    }

    constructor(itemClickListener: ((Table, Int) -> Unit)): super() {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)

        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val item = items[position]

        holder.table = item

        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderPair));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.holderOdd));
        }
    }

    fun setTables(tables: MutableList<Table>) {
        items.clear()
        items.addAll(tables)

        notifyDataSetChanged()
    }

    inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var table: Table? = null
            set(value) {
                itemView.findViewById<TextView>(R.id.table_name_text).text = value?.name
                field = value
            }

        init {
            itemView.setOnClickListener {
                table?.let {
                    itemClickListener?.invoke(table as Table, adapterPosition)
                }

            }

        }


    }
}