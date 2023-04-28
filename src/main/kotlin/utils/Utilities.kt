package utils

import models.Item
import models.ShoppingList

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(listToFormat: List<ShoppingList>): String =
        listToFormat
            .joinToString(separator = "\n") { shoppingList ->  "$shoppingList" }

    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Item>): String =
        itemsToFormat
            .joinToString(separator = "\n") { item ->  "\t$item" }

}
