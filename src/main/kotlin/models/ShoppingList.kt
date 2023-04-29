package models
import java.time.LocalDateTime

data class ShoppingList(
    val listId: Int,
    var listName: String,
    var author: String,
    var currentDateTime: LocalDateTime = LocalDateTime.now(), // default to current date
    var items: MutableSet<Item> = mutableSetOf()
) {

    private var lastItemID = 0
    // functions to manage the items

    // Add an ID to an Item
    private fun getItemId() = lastItemID++
    // Add new Item

    fun addItem(item: Item): Boolean {
        item.itemID = getItemId()
        return items.add(item)
    }
    fun deleteItem(id: Int): Boolean {
        return items.removeIf { item -> item.itemID == id }
    }

    fun findOne(id: Int): Item? {
        return items.find { item -> item.itemID == id }
    }

    fun updateItem(id: Int, newItem: Item): Boolean {
        val itemUpdate = findOne(id)
        if (itemUpdate != null) {
            itemUpdate.itemName = newItem.itemName
            itemUpdate.quantity = newItem.quantity
            itemUpdate.price = newItem.price
            return true
        }
        return false
    }

    fun listItems() =
        if (items.isEmpty()) "\t No Items Found"
        else items.mapIndexed { index, item -> "$index : $item" }.joinToString(separator = "\n")
}


/**
 * Author: Pawel Jaglarz
 * Version: 3.0
 * Description: This file contains the implementation of the ShoppingList data class, which represents a shopping list with a unique ID, name, author, creation date, and a set of items.
 * Usage: The ShoppingList class is used to create and manage individual shopping lists, and is used by the ListAPI class to store and retrieve shopping list data.
 */