package com.proyectodev.yummydroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.fragments.DishesListFragment

class DishesListActivity : AppCompatActivity(), DishesListFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes_list)

        if (savedInstanceState == null) {
            val fragment = DishesListFragment()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.dishes_list_container, fragment)
                    .commit()
        }
    }

    // Eventos de DishesListFragment
    override fun onShowDishClicked(dishIndex: Int) {
        val intent = DishDetailActivity.intent(this, dishIndex)
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
        startActivity(intent)
        finish()
    }
}
