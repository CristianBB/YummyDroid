package com.proyectodev.yummydroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.DishesListRecyclerViewAdapter
import com.proyectodev.yummydroid.model.Dishes

class DishesListActivity : AppCompatActivity() {

    val dishesList: RecyclerView by lazy {
        val dishesList: RecyclerView = findViewById(R.id.dishes_list)
        dishesList.layoutManager = LinearLayoutManager(this)
        dishesList
    }

    val adapter: DishesListRecyclerViewAdapter by lazy {
        val adapter = DishesListRecyclerViewAdapter{item, position ->
            showDish(position)
        }
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_list)

        dishesList.adapter = adapter
    }

    fun showDish(position: Int) {
        Log.d("Boton", "Se ha pulsado el plato ${Dishes.getDish(position)}")
    }
}
