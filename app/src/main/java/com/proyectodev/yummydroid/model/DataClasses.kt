package com.proyectodev.yummydroid.model

import android.media.Image
import com.proyectodev.yummydroid.R

enum class Allergens(val image: Int) {
    GLUTEN(R.drawable.ic_gluten),
    CRUSTACEANS(R.drawable.ic_crustaceans),
    EGG(R.drawable.ic_egg),
    FISH(R.drawable.ic_fish),
    PEANUT(R.drawable.ic_peanut),
    SOY(R.drawable.ic_soybean),
    MILK(R.drawable.ic_milk),
    ALMOND(R.drawable.ic_almond),
    CELERY(R.drawable.ic_celery),
    MUSTARD(R.drawable.ic_mustard),
    SESAME(R.drawable.ic_sesame),
    SULFIDE(R.drawable.ic_sulfide),
    MOLLUSCS(R.drawable.ic_mollusc),
    LUPINS(R.drawable.ic_lupin)
}


data class Dish(val name: String, val description: String, val image: Int, val allergens: List<Allergens>?, val price: Float)

data class Command(val dish: Dish, val variants: String)


data class Table(var name: String) {

    private var commands = mutableListOf<Command>()

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun removeCommand(command: Command) {
        commands.remove(command)
    }

    fun getCommands(): List<Command> {
        return commands
    }

    fun resetCommands() {
        commands = mutableListOf()
    }

}
