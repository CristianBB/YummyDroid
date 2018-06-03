package com.proyectodev.yummydroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.DishesListRecyclerViewAdapter

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
        val intent = DishDetailActivity.intent(this, position)
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
        startActivity(intent)
        finish()
    }
}
