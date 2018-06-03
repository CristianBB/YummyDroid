package com.proyectodev.yummydroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.proyectodev.yummydroid.R
import com.proyectodev.yummydroid.fragments.DishDetailFragment
import com.proyectodev.yummydroid.fragments.DishesListFragment
import com.proyectodev.yummydroid.fragments.TableDetailFragment
import com.proyectodev.yummydroid.fragments.TablesListFragment
import com.proyectodev.yummydroid.model.Command
import com.proyectodev.yummydroid.model.Dishes
import com.proyectodev.yummydroid.model.Tables
import kotlinx.android.synthetic.main.activity_tables_list.*

class TablesListActivity: AppCompatActivity(), TablesListFragment.OnItemClickListener, TableDetailFragment.OnItemClickListener, DishesListFragment.OnItemClickListener, DishDetailFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables_list)

        // Controlamos que únicamente se añada el fragment la primera vez
        if (savedInstanceState == null) {
            val fragment = TablesListFragment()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.list_container, fragment)
                    .commit()
        }

    }

    private fun isDetailViewAvailable() = detail_container != null

    // TablesListFragment
    override fun onTableClicked(tableIndex: Int) {

        if (isDetailViewAvailable()) {
            showTableDetailFragment(tableIndex)
        } else {
            startTableDetailActivity(tableIndex)
        }
    }

    private fun showTableDetailFragment(tableIndex: Int) {
        val tableDetailFragment = TableDetailFragment.newInstance(tableIndex)

        // Limpiamos el BackStack
        supportFragmentManager.popBackStack()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_container, tableDetailFragment, TableDetailActivity.TAG_TABLE_DETAIL_FRAGMENT)
                .commit()
    }

    private fun startTableDetailActivity(tableIndex: Int) {
        val intent: Intent = TableDetailActivity.intent(this, tableIndex)
        startActivity(intent)
    }


    // Eventos de TableDetailFragment. Estos eventos solo llegarán aquí si la navegación es por Tablet
    override fun onAddCommandClicked() {
        val dishesListFragment = DishesListFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_container, dishesListFragment)
                .addToBackStack(null)
                .commit()
    }

    // Eventos de DishesListFragment. Estos eventos solo llegarán aquí si la navegación es por Tablet
    override fun onShowDishClicked(dishIndex: Int) {
        val dishDetailFragment = DishDetailFragment.newInstance(dishIndex)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_container, dishDetailFragment)
                .addToBackStack(null)
                .commit()
    }

    // Eventos de DishDetailFragment. Estos eventos solo llegarán aquí si la navegación es por Tablet
    override fun onAddDishClicked(dishIndex: Int, variants: String) {

        // Creamos la comanda
        val dishAdded = Dishes.getDish(dishIndex)
        val command = Command(dishAdded, variants)

        // Recuperamos el fragment TableDetailFragment para obtener la Mesa
        val fragment = supportFragmentManager.findFragmentByTag(TableDetailActivity.TAG_TABLE_DETAIL_FRAGMENT) as TableDetailFragment
        val table = fragment.table

        // Añadimos la comanda a la mesa
        table.addCommand(command)

        // Usamos el metodo showTableDetailFragment que ya se encarga de limpiar la pila y mostrar el fragmento
        val tableIndex = Tables.getIndex(fragment.table)
        showTableDetailFragment(tableIndex)

    }

}
