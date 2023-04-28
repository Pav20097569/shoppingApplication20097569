package controllers

import models.ShoppingList
import java.util.*
import kotlin.collections.ArrayList
import ScannerInput.readNextInt
import ScannerInput.readNextLine
import models.Item

class ListAPI {
    private val scanner = Scanner(System.`in`)
    private val lists = ArrayList<ShoppingList>()

    fun findShoppingListById(id: Int): ShoppingList? {
        for (shoppingList in lists) {
            if (shoppingList.listId == id) {
                return shoppingList
            }
        }
        return null
    }

    fun selectShoppingList(): ShoppingList? {
        if (lists.isEmpty()) {
            println("There are no shopping lists created yet.")
            return null
        }

        // Print all the shopping lists with their ID and name
        println("Available shopping lists: ")
        for (shoppingList in lists) {
            println("ID: ${shoppingList.listId}, Name: ${shoppingList.listName}")
        }

        // Read the user's input for the list ID to select
        val listId = readNextInt("Enter the ID of the shopping list to select: ")

        // Find the shopping list with the given ID
        val selectedList = findShoppingListById(listId)
        if (selectedList == null) {
            println("Invalid shopping list ID entered.")
        }

        return selectedList
    }



    fun listAllShoppingLists() {
        if (lists.isEmpty()) {
            println("There are no shopping lists created yet.")
            return
        }

        println("All shopping lists: ")
        for (list in lists) {
            println("ID: ${list.listId}, Name: ${list.listName}")
            println("Items: ")
            for (item in list.items) {
                println("  Name: ${item.name}, Quantity: ${item.quantity}, Price: ${item.price}")
            }
            println()
        }
    }

}
