package models

data class List(
    val listName: String,
    val items: MutableList<Item> = mutableListOf()
)
