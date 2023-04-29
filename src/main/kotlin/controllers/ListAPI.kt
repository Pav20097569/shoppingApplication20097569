package controllers


import models.ShoppingList
import java.util.*
import kotlin.collections.ArrayList

import persistence.Serializer



class ListAPI(serializerType: Serializer) {
    private var lists = ArrayList<ShoppingList>()

    private var serializer: Serializer = serializerType
    private fun formatListString(listToFormat : List<ShoppingList>) : String =
        listToFormat.joinToString ( separator = "\n" ) {shoppingList -> lists.indexOf(shoppingList).toString() + ": " + shoppingList.toString()}

    fun add(shoppingList: ShoppingList): Boolean {
        return lists.add(shoppingList)
    }

    fun removeList(listDelete: ShoppingList): Boolean {
        return lists.remove(listDelete)
    }

    fun amountOfLists(): Int{
        return lists.size
    }

    fun listAllShoppingLists(): String =
        if (lists.isEmpty()) "No Shopping Lists Stored"
        else formatListString(lists)

    fun findShoppingListById(id: Int): ShoppingList? {
        for (shoppingList in lists) {
            if (shoppingList.listId == id) {
                return shoppingList
            }
        }
        return null
    }

    fun updateShoppingList(id: Int, updatedList: ShoppingList): Boolean {
        val updateList = findShoppingListById(id)

        if (updateList != null) {
            updateList.listName = updatedList.listName
            updateList.author = updatedList.author
            updateList.currentDateTime = updatedList.currentDateTime
            return true
        }
        return false
    }


    @Suppress("UNCHECKED_CAST")
    @Throws(Exception::class)
    fun load() {
        lists = serializer.read() as ArrayList<ShoppingList>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(lists)
    }
}





