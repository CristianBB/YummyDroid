package com.proyectodev.yummydroid.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.TablesListRecyclerViewAdapter
import com.proyectodev.yummydroid.model.Tables

class TablesListFragment: Fragment() {

    interface OnItemClickListener {
        fun onTableClicked(tableIndex: Int)
    }

    lateinit var clickListener: OnItemClickListener

    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

    val adapter: TablesListRecyclerViewAdapter by lazy {
        val adapter = TablesListRecyclerViewAdapter{item, position ->
            clickListener.onTableClicked(position)
        }
        adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener) {
            clickListener = context
        } else {
            throw IllegalArgumentException("Attached activity doesn't implement TablesListFragment.OnItemClickListener")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tables_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tables = Tables.getTables()
        adapter.setTables(tables)
        list.adapter = adapter
    }

}