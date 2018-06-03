package com.proyectodev.yummydroid.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.CommandsListRecyclerViewAdapter
import com.proyectodev.yummydroid.fragments.TableDetailFragment
import com.proyectodev.yummydroid.model.Command
import com.proyectodev.yummydroid.model.Dishes
import com.proyectodev.yummydroid.model.Table
import com.proyectodev.yummydroid.model.Tables

class TableDetailActivity: AppCompatActivity(), TableDetailFragment.OnItemClickListener {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val REQUEST_COMMAND_DISH = 1
        val TAG_TABLE_DETAIL_FRAGMENT = "TAG_TABLE_DETAIL_FRAGMENT"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)

        val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)

        if (savedInstanceState == null) {
            val fragment = TableDetailFragment.newInstance(tableIndex)

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.table_detail_container, fragment, TAG_TABLE_DETAIL_FRAGMENT)
                    .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_COMMAND_DISH && resultCode == Activity.RESULT_OK) {
            data?.let {
                val dishIndex = it.getIntExtra(DishDetailActivity.EXTRA_DISH_INDEX, 0)
                val variants = it.getStringExtra(DishDetailActivity.EXTRA_VARIANTS)

                val dishAdded = Dishes.getDish(dishIndex)
                val command = Command(dishAdded, variants)

                // Obtenemos el fragmento de detalle
                val fragment = supportFragmentManager.findFragmentByTag(TAG_TABLE_DETAIL_FRAGMENT) as TableDetailFragment

                // Agregamos la comanda recibida y avisamos al adapter de los cambios usando el Fragment
                fragment.table.addCommand(command)
                fragment.adapter.notifyDataSetChanged()
            }
        }
    }

    // Eventos de TableDetailFragment
    override fun onAddCommandClicked() {
        val intent = Intent(this, DishesListActivity::class.java)
        startActivityForResult(intent, TableDetailActivity.REQUEST_COMMAND_DISH)
    }

}