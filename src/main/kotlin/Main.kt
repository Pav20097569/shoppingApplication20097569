import ScannerInput.readNextInt
import ScannerInput.readNextLine
import ScannerInput.readNextDouble
import controllers.ListAPI
import models.Item
import models.ShoppingList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


private val listAPI = ListAPI()
fun main() = runMenu()


fun runMenu(){
    do{
        val option = mainMenu()
        when (option) {
            1 -> addShoppingList()
            2 -> updateShoppingList()
            //3 -> addItem()
            4 -> removeItemFromList()
            5 -> editItemOnList()
            6 -> displayShoppingList()
            7 -> calculateTotalPrice()
            8 -> listAllShoppingLists()
            0 -> exitApp()


            else -> println("Invalid option entered. Please try again")
        }
    } while (true)
}



fun mainMenu() = readNextInt(

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
            |   6. Calculate total price  
            |  -------------------------------  |
            |   7. List All Shopping Lists      |      
            |   8.                              |
            |   9.                              |
            |   0. Exit                         |
            -------------------------------------
            ==>> """.trimMargin(">")
   )





fun addShoppingList() {
    val listId = Random.nextInt()
    val listName = readNextLine("Enter a name for the Shopping List:  ")
    val author = readNextLine("Enter Your Name: ")
    val currentDateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val isAdded = listAPI.add(ShoppingList(listId,listName,author))

    println("New shopping list '$listName' created on ${currentDateTime.format(dateFormatter)} at ${currentDateTime.format(timeFormatter)} with ID: '$listId' ")

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun listAllShoppingLists() {
    println(listAPI.listAllShoppingLists())
}

fun updateShoppingList(){

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