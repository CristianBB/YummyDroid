package com.proyectodev.yummydroid.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.adapter.CommandsListRecyclerViewAdapter
import com.proyectodev.yummydroid.model.Table
import com.proyectodev.yummydroid.model.Tables
import kotlinx.android.synthetic.main.fragment_table_detail.*

class TableDetailFragment: Fragment() {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun newInstance(tableIndex: Int): TableDetailFragment {
            val instance = TableDetailFragment()

            val args = Bundle()
            args.putInt(EXTRA_TABLE_INDEX, tableIndex)

            instance.arguments = args
            return instance
        }
    }

    interface OnItemClickListener {
        fun onAddCommandClicked()
    }

    lateinit var clickListener: OnItemClickListener

    val table: Table by lazy {
        /// Sacamos los datos con los que configurar la interfaz
        val table = Tables[arguments!!.getInt(EXTRA_TABLE_INDEX, 0)]
        table
    }

    val commandList: RecyclerView by lazy {
        val commandList: RecyclerView = detail_command_list
        commandList.layoutManager = LinearLayoutManager(context)
        commandList
    }

    val adapter: CommandsListRecyclerViewAdapter by lazy {
        val adapter = CommandsListRecyclerViewAdapter(table,
                {item, position ->
                    removeCommand(position)
                })
        adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener) {
            clickListener = context
        } else {
            throw IllegalArgumentException("Attached activity doesn't implement TableDetailFragment.OnItemClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_add_command_btn.setOnClickListener {
            clickListener.onAddCommandClicked()
        }

        // Actualizamos la interfaz
        detail_table_name_text.text = table.name

        commandList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_table_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_bill -> {
                showAlertBill()
                return true
            }
            R.id.menu_reset -> {
                showAlertReset()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun removeCommand(position: Int) {
        val removedCommand = table.getCommand(position)
        table.removeCommand(removedCommand)
        adapter.notifyDataSetChanged()
    }

    fun showAlertReset() {
        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("Vaciar Mesa")
        builder.setMessage("Las comandas actuales se perderán, ¿Desea continuar?")

        builder.setPositiveButton("Aceptar"){dialog, which ->
            table.resetCommands()
            adapter.notifyDataSetChanged()
            Toast.makeText(context, "Comandas eliminadas", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancelar"){dialog, which -> }
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.show()
    }

    fun showAlertBill() {
        val builder = AlertDialog.Builder(context!!)

        var totalPrice = 0F
        table.getCommands().forEach {
            totalPrice = totalPrice + it.dish.price
        }

        builder.setTitle("Cuenta de la mesa ${table.name}")
        builder.setMessage("${totalPrice} €")
        builder.setPositiveButton("Aceptar"){dialog, which -> }
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.show()
    }
}