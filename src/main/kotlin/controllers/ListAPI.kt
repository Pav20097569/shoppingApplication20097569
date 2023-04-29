package controllers

import ScannerInput.readNextDouble
import models.ShoppingList
import java.util.*
import kotlin.collections.ArrayList
import ScannerInput.readNextInt
import models.Item
import utils.Utilities.formatListString


class ListAPI {
    private var lists = ArrayList<ShoppingList>()
    private fun formatListString(listToFormat : List<ShoppingList>) : String =
        listToFormat.joinToString ( separator = "\n" ) {shoppingList -> lists.indexOf(shoppingList).toString() + ": " + shoppingList.toString()}

    fun add(shoppingList: ShoppingList): Boolean {
        return lists.add(shoppingList)
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
            return null // Return null if the shopping list is not found
        }

        return selectedList // Return the selected shopping list
    }
}





