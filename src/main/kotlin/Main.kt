import ScannerInput.readNextInt
import ScannerInput.readNextLine
import ScannerInput.readNextDouble
import controllers.ListAPI
import models.Item
import models.ShoppingList
import persistence.JSONSerializer
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.system.exitProcess

private val listAPI = ListAPI(JSONSerializer(File("ShoppingLists.json")))

fun main() = runMenu()

fun runMenu() {
    do {
        when (mainMenu()) {
            1 -> shoppingListOptions()
            2 -> itemOptions()
            3 -> listAllLists()
            4 -> amountOfLists()
            10 -> load()
            20 -> save()
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
        |   4. Amount of Shopping Lists     |
        |   0. Exit                         |
        -------------------------------------
        |   10. Load File                   |
        |   20. Save File                   |
        -------------------------------------
        ==>> """.trimMargin(">"))


fun shoppingListOptions() {

    val option = readNextInt(
        """
        -------------------------------------
        |      SHOPPING LIST OPTIONS        |
        -------------------------------------
        | Options:                          |
        |   1. Add Shopping List            |
        |   2. Update shopping list         |
        |   3. Delete shopping list         |
        |   0. Back to main menu            |
        -------------------------------------
        ==>> """.trimMargin(">"))

    when (option) {
        1 -> addShoppingList()
        2 -> updateShoppingList()
        3 -> removeList()
        //    5 -> calculateTotalPrice()
        0 -> mainMenu()
    }
}

fun itemOptions() {
    val option = readNextInt(
        """
        -------------------------------------
        |         ITEM OPTIONS              |
        -------------------------------------
        | Options:                          |
        |   1. Add Item to List             |
        |   2. Remove Item from List        |
        |   3. Edit Item                    |
        |   0. Back to main menu            |
        -------------------------------------
        ==>> """.trimMargin(">"))

    when (option) {
        1 -> addItem()
        2 -> removeListItem()
        3 -> editItemOnList()
        0 -> mainMenu()
    }
}

fun checkLists(): Boolean {
    val lists = listAPI.listAllShoppingLists()
    if (lists == "No Shopping Lists Stored") {
        println(lists)
        return false
    }
    return true
}
// Adds an item to the shopping list
fun addItem() {
    // Displays all the shopping lists
    listAllLists()

    // Checks if there is at least one shopping list available
    if (!checkLists()) {
        return
    }

    // Prompts the user to select a shopping list by ID
    val shoppingList: ShoppingList? = listAPI.findShoppingListById(
        readNextInt("Enter the ID of your Shopping List Please: ")
    )

    if (shoppingList != null) {
        // Generates a random ID for the new item
        val itemId = Random.nextInt()

        // Prompts the user to enter the item name, price, and quantity
        val itemName = readNextLine("Please Enter the Item Name: ")
        val price = readNextDouble("Please Enter the Price of the Item: ")
        val quantity = readNextInt("Please enter the amount of the Item you would like to add: ")

        // Adds the new item to the shopping list and prints a success message
        if (shoppingList.addItem(Item(itemID = itemId, itemName = itemName, price = price, quantity = quantity))) {
            println("$itemName Added To The Shopping List")
        }
    }
}

// Adds a new shopping list
fun addShoppingList() {
    // Generates a random ID for the new shopping list
    val listId = Random.nextInt()

    // Prompts the user to enter the shopping list name and author
    val listName = readNextLine("Enter a name for the Shopping List:  ")
    val author = readNextLine("Enter Your Name: ")

    // Gets the current date and time
    val currentDateTime = LocalDateTime.now()
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    // Adds the new shopping list and prints a success message
    val isAdded = listAPI.add(ShoppingList(listId,listName,author))
    println("New shopping list '$listName' created on ${currentDateTime.format(dateFormatter)} at ${currentDateTime.format(timeFormatter)} with ID: '$listId' ")

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

// Displays all the shopping lists
fun listAllLists() {
    println(listAPI.listAllShoppingLists())
}

// Updates a shopping list with a new name and author
fun updateShoppingList() {
    listAllLists()
    if (!checkLists()) {
        return
    }

    val shoppingListId = readNextInt("Please enter the ID of the shopping list you want to change: ")
    val newName = readNextLine("Enter new name for the shopping list: ")
    val newAuthor = readNextLine("Enter your name please: ")
    val currentDateTime = LocalDateTime.now()

    if (listAPI.updateShoppingList(shoppingListId, ShoppingList(listId = shoppingListId, listName = newName, author = newAuthor, currentDateTime = currentDateTime))) {
        println("Shopping list successfully updated.")
    }
}

// Removes a shopping list by ID
fun removeList() {
    listAllLists()
    if (!checkLists()) {
        return
    }

    val shoppingList: ShoppingList? = listAPI.findShoppingListById(readNextInt("Please enter the ID of the shopping list you want to remove: "))
    if (shoppingList != null) {
        if (listAPI.removeList(shoppingList)) {
            println("Shopping list removed.")
        }
    }
}

// Removes an item from a shopping list by ID and index
fun removeListItem() {
    if (!checkLists()) {
        return
    }

    val shoppingList: ShoppingList? = listAPI.findShoppingListById(readNextInt("Please enter the ID of the shopping list containing the product: "))
    if (shoppingList != null) {
        println(shoppingList.listItems())
        if (shoppingList.deleteItem(readNextInt("Please enter the index of the item you wish to delete: "))) {
            println("Item deleted.")
        }
    }
}


//Function to edit an item on a shopping list by ShoppingListId and item Index
fun editItemOnList() {
    listAllLists()
    if(!checkLists()) {
        return
    }
    val shoppingList: ShoppingList? = listAPI.findShoppingListById(readNextInt("Enter the ID of the shopping list to edit an item on: "))
    println(shoppingList?.listItems())

    val itemIndex: Int = readNextInt("Please enter the Index of the Item you would like to Update: ")
    val newItemName = readNextLine("Please enter the New Name of the Item: ")
    val newPrice = readNextDouble("Please enter the New Price of the Item: ")
    val newQuantity = readNextInt("Please enter the new amount of the item: ")

    if(shoppingList?. updateItem(itemIndex, Item(itemID = itemIndex,itemName = newItemName, price = newPrice, quantity = newQuantity)) == true) {
        println("Item Updated!")
    }
}

// Shows the count of total amount of Shopping Lists
fun amountOfLists() {
    println("        Current  Amount of Shopping Lists in the System: "  + listAPI.amountOfLists())
}



fun exitApp(){
    println("Exiting...bye")
    exitProcess(0)
}


//Saves to file
fun save() {
    try {
        listAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

//Loads from File
fun load() {
    try {
        listAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}