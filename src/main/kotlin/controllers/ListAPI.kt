package controllers

import models.ShoppingList
import persistence.Serializer
import kotlin.collections.ArrayList

class ListAPI(serializerType: Serializer) {
    private var lists = ArrayList<ShoppingList>()

    // Private property to hold the serializer instance
    private var serializer: Serializer = serializerType

    // Function to format the list of ShoppingLists as a string
    private fun formatListString(listToFormat: List<ShoppingList>): String =
        listToFormat.joinToString(separator = "\n") { shoppingList -> lists.indexOf(shoppingList).toString() + ": " + shoppingList.toString() }

    // Function to add a ShoppingList to the list
    fun add(shoppingList: ShoppingList): Boolean {
        return lists.add(shoppingList)
    }

    // Function to remove a ShoppingList from the list
    fun removeList(listDelete: ShoppingList): Boolean {
        return lists.remove(listDelete)
    }

    // Function to return the number of ShoppingLists in the list
    fun amountOfLists(): Int {
        return lists.size
    }

    // Function to return a string representation of all ShoppingLists in the list
    fun listAllShoppingLists(): String =
        if (lists.isEmpty()) "No Shopping Lists Stored"
        else formatListString(lists)

    // Function to find a ShoppingList in the list by its ID
    fun findShoppingListById(id: Int): ShoppingList? {
        for (shoppingList in lists) {
            if (shoppingList.listId == id) {
                return shoppingList
            }
        }
        return null
    }

    // Function to update the details of a ShoppingList in the list
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

    // Function to load the ShoppingLists from storage using the serializer
    @Suppress("UNCHECKED_CAST")
    @Throws(Exception::class)
    fun load() {
        lists = serializer.read() as ArrayList<ShoppingList>
    }

    // Function to store the ShoppingLists to storage using the serializer
    @Throws(Exception::class)
    fun store() {
        serializer.write(lists)
    }
}

/**
 * Author: Pawel Jaglarz
 * Version: 3.0
 * Description: This file contains the implementation of the ListAPI class, which provides methods for managing shopping lists.
 * Usage: The ListAPI class is instantiated with a JSONSerializer object, which loads and saves shopping list data to a JSON file. The class provides methods for adding, deleting, and updating shopping lists and their items.
*/