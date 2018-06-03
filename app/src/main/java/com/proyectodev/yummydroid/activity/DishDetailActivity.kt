package com.proyectodev.yummydroid.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Dish
import com.proyectodev.yummydroid.model.Dishes
import android.app.Activity
import com.proyectodev.yummydroid.fragments.DishDetailFragment
import java.text.DecimalFormat


class DishDetailActivity : AppCompatActivity(), DishDetailFragment.OnItemClickListener {

    companion object {

        val EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX"
        val EXTRA_VARIANTS = "EXTRA_VARIANTS"

        fun intent(context: Context, dishIndex: Int): Intent {
            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra(EXTRA_DISH_INDEX, dishIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val dishIndex = intent.getIntExtra(EXTRA_DISH_INDEX, 0)

        if (savedInstanceState == null) {
            val fragment = DishDetailFragment.newInstance(dishIndex)

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.dish_detail_container, fragment)
                    .commit()
        }

    }

    override fun onAddDishClicked(dishIndex: Int, variants: String) {
        val returnIntent = Intent()
        returnIntent.putExtra(EXTRA_DISH_INDEX, dishIndex)
        returnIntent.putExtra(EXTRA_VARIANTS, variants)

        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
