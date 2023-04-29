package models
data class Item(
    var itemID: Int,
    var itemName: String,
    var quantity: Int,
    var price: Double
)

/**
 * Author: Pawel
 * Version: [Version Number]
 * Description: This file contains the implementation of the Item data class, which represents an item in a shopping list with a unique ID, name, quantity, and price.
 * Usage: The Item class is used to create and manage individual items within a shopping list, and is used by the ShoppingList class to store and retrieve item data.
 */
