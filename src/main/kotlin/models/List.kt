package models
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class List(
    var listName: String,
    var author: String,
    val currentDateTime: LocalDateTime = LocalDateTime.now(), // default to current date
    var items: MutableList<Item> = mutableListOf()
)
