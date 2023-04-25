import java.util.*
import java.util.Scanner
import java.lang.System.exit



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


   print( """
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
            ==>> """.trimMargin(">"))
    return scanner.nextInt()
}


fun createShoppingList() {
    // code to create a new shopping list
    println("A new shopping list has been created.")
}

fun addItemToList() {
    println("Enter the name of the shopping list to add an item to:")
    val listName = readLine()
    // code to add an item to the shopping list with name "listName"
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