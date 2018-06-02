package com.proyectodev.yummydroid.model

import com.proyectodev.yummydroid.R

object Tables {
    private val tables: MutableList<Table> = mutableListOf(
            Table("Mesa 1"),
            Table("Mesa 2"),
            Table("Mesa 3"),
            Table("Mesa 4"),
            Table("Mesa 5"),
            Table("Mesa 6")
    )

    val count
        get() = tables.size

    fun getTable(index: Int) = tables[index]

    fun getIndex(table: Table) = tables.indexOf(table)

    fun getTables(): MutableList<Table> {
        return tables
    }

    operator fun get(index: Int) = tables[index]

}
