package com.proyectodev.yummydroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.TablesListRecyclerViewAdapter
import com.proyectodev.yummydroid.model.Tables

class TablesListActivity: AppCompatActivity() {

    val list: RecyclerView by lazy {
        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list
    }

    val adapter: TablesListRecyclerViewAdapter by lazy {
        val adapter = TablesListRecyclerViewAdapter{item, position ->
            showTable(position)
        }
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables_list)

        val tables = Tables.getTables()
        adapter.setTables(tables)
        list.adapter = adapter
    }

    fun showTable(position: Int) {

        val intent = TableDetailActivity.intent(this, position)
        startActivity(intent)

    }
}
