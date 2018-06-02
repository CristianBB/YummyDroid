package com.proyectodev.yummydroid.model

import com.proyectodev.yummydroid.R

object Dishes {
    private val dishes: List<Dish> = listOf(
            Dish("Huevos estrellados", "En aceite de oliva con jamón ibérico y patatas fritas", R.drawable.huevos_estrellados, listOf(Allergens.EGG), 9.90f),
            Dish("Brocheta de solomillo ibérico", "Con salsa curry, verduritas y patatas fritas", R.drawable.brochetas_solomillo, listOf(Allergens.MUSTARD, Allergens.MILK, Allergens.CELERY), 10.90f),
            Dish("Codillo de cerdo asado", "Con salsa agridulce y guarnición de col .", R.drawable.codillo_asado, listOf(Allergens.SOY, Allergens.GLUTEN), 11.90f),
            Dish("Chuletillas de cordero", "Con huevo frito, croquetas y patatas fritas", R.drawable.chuletillas, listOf(Allergens.EGG, Allergens.MILK, Allergens.GLUTEN), 12.90f),
            Dish("Pechuga de pollo crujiente", "Con mozzarela, bacon, salsa de miel y mostaza, huevo frito, croquetas y patatas fritas ", R.drawable.pollo_crujiente,listOf(Allergens.MUSTARD, Allergens.MILK, Allergens.CELERY, Allergens.EGG, Allergens.GLUTEN), 12.90f),
            Dish("Tortellini relleno de carne", "Con tu salsa preferida: Funghi, pesto genovese, tomate, queso o carbonara", R.drawable.tortellini, listOf(Allergens.EGG, Allergens.MILK, Allergens.GLUTEN), 7.50f),
            Dish("Berenjena gratinada ", "De ternera y champiñones", R.drawable.berenjena, listOf(Allergens.MILK), 7.50f),
            Dish("Helado crocanti de almendras", "", R.drawable.helado_crocanti, listOf(Allergens.MILK, Allergens.ALMOND, Allergens.GLUTEN), 3.90f),
            Dish("Helado de galleta OREO", "", R.drawable.helado_oreo,listOf(Allergens.MILK, Allergens.GLUTEN, Allergens.SOY, Allergens.PEANUT), 3.90f),
            Dish("Brownie de chocolate y nueces", "Con helado de vainilla cubierto de chocolate fundido", R.drawable.brownie, listOf(Allergens.MILK, Allergens.EGG, Allergens.PEANUT, Allergens.GLUTEN), 4.90f)
    )

    val count
        get() = dishes.size

    fun getDish(index: Int) = dishes[index]

    fun getIndex(dish: Dish) = dishes.indexOf(dish)

    operator fun get(index: Int) = dishes[index]

}
