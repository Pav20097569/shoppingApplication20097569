import ScannerInput.readNextInt
import ScannerInput.readNextLine
import ScannerInput.readNextDouble
import controllers.ListAPI
import models.Item
import models.ShoppingList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import java.util.*


private val listAPI = ListAPI()
fun main() = runMenu()


fun runMenu(){
    do{
        val option = mainMenu()
        when (option) {
            1 -> ShoppingListOptions()
            2 -> ItemOptions()
            3 -> listAllLists()
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
        |   1. Shopping list options        |
        |   2. Item Options                 |
        |   3. List All Shopping Lists      |
        |   0. Exit                         |
        -------------------------------------
        ==>> """.trimMargin(">"))

fun ShoppingListOptions(){
    val option = readNextInt(
        """
        -------------------------------------
        |      SHOPPING LIST OPTIONS        |
        -------------------------------------
        | Options:                          |
        |   1. Add Shopping List            |
        |   2. Update shopping list         |
        |   3. Delete shopping list         |
        |   4. Calculate Total Price
        |   0. Back to main menu            |
        -------------------------------------
        ==>> """.trimMargin(">"))

    when (option) {
        1 -> addShoppingList()
        2 -> updateShoppingList()
        3 -> removeItemFromList()
        4 -> calculateTotalPrice()
        0 -> mainMenu()
    }
}


fun ItemOptions() {
    val option = readNextInt(
    """
        -------------------------------------
        |         ITEM OPTIONS              |
        -------------------------------------
        | Options:                          |
        |   1. Add Item to List             |
        |   2. Remove Item from List        |
        |   0. Back to main menu            |
        -------------------------------------
        ==>> """.trimMargin(">"))

    when (option) {
        1 -> addItem()
        2 -> removeItemFromList()
        0 ->mainMenu()
    }
}



fun checkLists(): Boolean {
    val lists = listAPI.listAllShoppingLists()
    if (lists == "No Shopping Lists Stored"){
        println(lists)
        return false
    }
    return true
}
fun addItem() {
    if (!checkLists()) {
        return
    }
    val list: ShoppingList? = listAPI.findShoppingListById(readNextInt("Enter the ID of your Shopping List Please: "))


    if (list != null) {
        val itemId = Random.nextInt()
        val itemName = readNextLine("Please Enter the Item Name: ")
        val price = readNextDouble("Please Enter the Price of the Item: ")
        val quantity = readNextInt("Please enter the amount of the Item you would like to add: ")
        if (list.addItem(Item(itemID = itemId, itemName = itemName, price = price, quantity = quantity))){
            println("$itemName Added To The Shopping List")
        }

    }
}
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


fun listAllLists() {
    println(listAPI.listAllShoppingLists())
}

fun updateShoppingList(){
    if(!checkLists()) {
        return
    }
    val listID = readNextInt(" Please Enter the ID of the Shopping List you are trying to Change: " )

    val newListName = readNextLine("Enter New Name for the Shopping List: ")
    val  newAuthor = readNextLine("Enter your name please: ")
    val newcurrentDateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    if(listAPI.updateShoppingList(listID,ShoppingList(listId = listID,listName = newListName, author = newAuthor, currentDateTime = newcurrentDateTime))){
        println("Shopping List Successfully Updated")
    }
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