package com.proyectodev.yummydroid.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.DishesListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_dishes_list.*

class DishesListFragment: Fragment() {

    interface OnItemClickListener {
        fun onShowDishClicked(dishIndex: Int)
    }

    lateinit var clickListener: OnItemClickListener

    val dishesList: RecyclerView by lazy {
        val dishesList: RecyclerView = dishes_list
        dishesList.layoutManager = LinearLayoutManager(context)
        dishesList
    }

    val adapter: DishesListRecyclerViewAdapter by lazy {
        val adapter = DishesListRecyclerViewAdapter{item, position ->
            clickListener.onShowDishClicked(position)
        }
        adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener) {
            clickListener = context
        } else {
            throw IllegalArgumentException("Attached activity doesn't implement DishesListFragment.OnItemClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dishes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dishesList.adapter = adapter
    }
}