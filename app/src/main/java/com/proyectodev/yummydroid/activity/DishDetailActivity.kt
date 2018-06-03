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
import java.text.DecimalFormat


class DishDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX"
        val EXTRA_VARIANTS = "EXTRA_VARIANTS"

        fun intent(context: Context, dishIndex: Int): Intent {
            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra(EXTRA_DISH_INDEX, dishIndex)

            return intent
        }
    }

    val dish: Dish by lazy {
        // Sacamos los datos con los que configurar la interfaz
        val dish = Dishes[intent.getIntExtra(DishDetailActivity.EXTRA_DISH_INDEX, 0)]
        dish
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        // Obtenemos referencia a los elementos de la interfaz
        val dishImage = findViewById<ImageView>(R.id.detail_dish_image)
        val nameText = findViewById<TextView>(R.id.detail_dish_name_text)
        val descriptionText = findViewById<TextView>(R.id.detail_dish_description_text)
        val priceText = findViewById<TextView>(R.id.detail_dish_price_text)
        val allergensLayout = findViewById<LinearLayout>(R.id.detail_allergens_layout)
        val addBtn = findViewById<FloatingActionButton>(R.id.detail_add_dish_btn)

        addBtn.setOnClickListener {
            addDish()
        }

        // Actualizamos la interfaz
        dishImage.setImageResource(dish.image)
        nameText.text = dish.name
        descriptionText.text = dish.description
        priceText.text = "${dish.price} €"

        dish.allergens?.let {
            it.forEach {
                val imageView = ImageView(this)
                imageView.setImageResource(it.image)
                imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                allergensLayout.addView(imageView)
            }
        }
    }

    fun addDish() {
        // Obtenemos referencia a las instrucciones para cocina
        val variantsText = findViewById<EditText>(R.id.detail_variants_text)
        val variantsString = variantsText.text.toString()

        val returnIntent = Intent()
        returnIntent.putExtra(EXTRA_DISH_INDEX, Dishes.getIndex(dish))
        returnIntent.putExtra(EXTRA_VARIANTS, variantsString)

        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
