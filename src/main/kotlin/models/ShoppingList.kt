package models
import java.time.LocalDateTime

data class ShoppingList(
    val listId: Int,
    var listName: String,
    var author: String,
    val currentDateTime: LocalDateTime = LocalDateTime.now(), // default to current date
    var items: MutableSet<Item> = mutableSetOf()
)
