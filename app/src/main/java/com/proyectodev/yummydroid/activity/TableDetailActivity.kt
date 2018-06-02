package com.proyectodev.yummydroid.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.CommandsListRecyclerViewAdapter
import com.proyectodev.yummydroid.model.Command
import com.proyectodev.yummydroid.model.Dishes
import com.proyectodev.yummydroid.model.Table
import com.proyectodev.yummydroid.model.Tables

class TableDetailActivity: AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    val table: Table by lazy {
        // Sacamos los datos con los que configurar la interfaz
        val table = Tables[intent.getIntExtra(EXTRA_TABLE_INDEX, 0)]
        table
    }

    val commandList: RecyclerView by lazy {
        val commandList: RecyclerView = findViewById(R.id.detail_command_list)
        commandList.layoutManager = LinearLayoutManager(this)
        commandList
    }

    val adapter: CommandsListRecyclerViewAdapter by lazy {
        val adapter = CommandsListRecyclerViewAdapter(table,
                {item, position ->
                    removeCommand(position)
                })
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)

        // Obtenemos referencia a los elementos de la interfaz
        val tableNameText = findViewById<TextView>(R.id.detail_table_name_text)
        var addCommandBtn = findViewById<FloatingActionButton>(R.id.detail_add_command_btn)

        addCommandBtn.setOnClickListener {
            addCommand()
        }

        // Actualizamos la interfaz
        tableNameText.text = table.name

        commandList.adapter = adapter
    }

    fun addCommand() {
        val intent = Intent(this, DishesListActivity::class.java)
        startActivity(intent)
    }

    fun removeCommand(position: Int) {
        val removedCommand = table.getCommand(position)
        table.removeCommand(removedCommand)
        adapter.notifyDataSetChanged()
    }
}