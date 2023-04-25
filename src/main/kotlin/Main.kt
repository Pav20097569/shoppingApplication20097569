import java.util.*
import java.util.Scanner
import java.lang.System.exit
import ScannerInput.readNextInt
import ScannerInput.readNextLine
import models.List
import controllers.ListAPI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    runMenu()
}

fun runMenu(){
    do{
        val option = mainMenu()
        when (option) {
            1 -> createShoppingList()
            2 -> addItemToList()
            3 -> removeItemFromList()
            4 -> editItemOnList()
            5 -> displayShoppingList()
            6 -> calculateTotalPrice()
            0 -> exitApp()


            else -> println("Invalid option entered. Please try again")
        }
    } while (true)
}



fun mainMenu() : Int {
return readNextInt(

   """
            -------------------------------------
            |      SHOPPING APP MENU            |
            -------------------------------------
            | Options:                          |
            |   1. Create a new shopping list   |
            |   2. Add an item to a list        |
            |   3. Remove an item from a list   |
            |   4. Edit an item on a list       |
            |   5. Display shopping list        |
            |   6. Calculate total price        |
            |   0. Exit                         |
            -------------------------------------
            ==>> """.trimMargin(">")
   )
}


fun createShoppingList() {
    val listName = readNextLine("Enter a name for the Shopping List:  ")
    val currentDateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    println("New shopping list '$listName' created on ${currentDateTime.format(dateFormatter)} at ${currentDateTime.format(timeFormatter)}")
}




fun addItemToList() {

}


fun removeItemFromList() {
    println("Enter the name of the shopping list to remove an item from:")
    val listName = readLine()
    // code to remove an item from the shopping list with name "listName"
}

fun editItemOnList() {
    println("Enter the name of the shopping list to edit an item on:")
    val listName = readLine()
    // code to edit an item on the shopping list with name "listName"
}

fun displayShoppingList() {
    println("Enter the name of the shopping list to display:")
    val listName = readLine()
    // code to display the shopping list with name "listName"
}

fun calculateTotalPrice() {
    println("Enter the name of the shopping list to calculate the total price of:")
    val listName = readLine()
    // code to calculate the total price of the shopping list with name "listName"
}

fun exitApp(){
    println("Exiting...bye")
    System.exit(0)
}