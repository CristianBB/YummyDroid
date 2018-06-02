package com.proyectodev.yummydroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.proyectodev.yummydroid.R

class TablesListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables_list)

        val list = findViewById<RecyclerView>(R.id.list)

        list.layoutManager = LinearLayoutManager
    }
}
