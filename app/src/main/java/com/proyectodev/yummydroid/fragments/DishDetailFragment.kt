package com.proyectodev.yummydroid.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.model.Dish
import com.proyectodev.yummydroid.model.Dishes
import kotlinx.android.synthetic.main.fragment_dish_detail.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class DishDetailFragment: Fragment() {

    companion object {

        val EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX"

        fun newInstance(dishIndex: Int): DishDetailFragment {
            val instance = DishDetailFragment()

            val args = Bundle()
            args.putInt(EXTRA_DISH_INDEX, dishIndex)

            instance.arguments = args
            return instance
        }
    }

    interface OnItemClickListener {
        fun onAddDishClicked(dishIndex: Int, variants: String)
    }

    lateinit var clickListener: OnItemClickListener

    val dish: Dish by lazy {
        // Sacamos los datos con los que configurar la interfaz
        val dish = Dishes[arguments!!.getInt(EXTRA_DISH_INDEX, 0)]
        dish
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener) {
            clickListener = context
        } else {
            throw IllegalArgumentException("Attached activity doesn't implement DishDetailFragment.OnItemClickListener")
        }
    }

    override fun onPause() {
        hideSoftKeyboard()
        super.onPause()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dish_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_add_dish_btn.setOnClickListener {
            hideSoftKeyboard()
            val variantsText = detail_variants_text.text.toString()
            clickListener.onAddDishClicked(Dishes.getIndex(dish), variantsText)
        }

        // Actualizamos la interfaz
        detail_dish_image.setImageResource(dish.image)
        detail_dish_name_text.text = dish.name
        detail_dish_description_text.text = dish.description
        detail_dish_price_text.text = "${dish.price} €"

        // Creamos imagenes de los alergenos de manera dinamica
        dish.allergens?.let {
            it.forEach {
                val imageView = ImageView(context)
                imageView.setImageResource(it.image)
                imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                detail_allergens_layout.addView(imageView)
            }
        }
    }

    // Brujeria para ocultar el teclado
    private fun hideSoftKeyboard() {
        val inputMethodManager = getActivity()?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}