package models
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ShoppingListTest {
    @Test
    fun `test addItem adds the item to the shopping list`() {
        val shoppingList = ShoppingList(1, "My List", "John Doe")
        val item = Item(1,"Eggs", 5, 2.00)
        shoppingList.addItem(item)
        assertEquals(1, shoppingList.items.size)
        assertTrue(shoppingList.items.contains(item))
    }

    @Test
    fun `test deleteItem removes the item from the shopping list`() {
        val shoppingList = ShoppingList(1, "My List", "John Doe")
        val item = Item(1, "Milk", 2, 2.99)
        shoppingList.addItem(item)
        shoppingList.deleteItem(item.itemID)
        assertEquals(0, shoppingList.items.size)
        assertFalse(shoppingList.items.contains(item))
    }

    @Test
    fun `test findOne returns the item with the given ID`() {
        val shoppingList = ShoppingList(1, "My List", "John Doe")
        val item1 = Item(1, "Milk", 2, 2.99)
        val item2 = Item(2, "Bread", 1, 1.99)
        shoppingList.addItem(item1)
        shoppingList.addItem(item2)
        assertEquals(item1, shoppingList.findOne(item1.itemID))
        assertEquals(item2, shoppingList.findOne(item2.itemID))
    }

    @Test
    fun `test updateItem updates the item with the given ID`() {
        val shoppingList = ShoppingList(1, "My List", "John Doe")
        val item1 = Item(1, "Milk", 2, 2.99)
        val item2 = Item(2, "Bread", 1, 1.99)
        shoppingList.addItem(item1)
        shoppingList.addItem(item2)
        val newItem = Item(1, "Cheese", 1, 3.99)
        assertTrue(shoppingList.updateItem(item1.itemID, newItem))
        assertEquals(newItem, shoppingList.findOne(item1.itemID))
    }

    @Test
    fun `test listItems returns a string representation of all items in the shopping list`() {
        val shoppingList = ShoppingList(1, "My List", "John Doe")
        val item1 = Item(1, "Milk", 2, 2.99)
        val item2 = Item(2, "Bread", 1, 1.99)
        shoppingList.addItem(item1)
        shoppingList.addItem(item2)
        val expectedList = "0 : Item(id=1, itemName=Milk, price=2.99, quantity=2)\n1 : Item(id=2, itemName=Bread, price=1.99, quantity=1)"
        assertEquals(expectedList, shoppingList.listItems())
    }
}

