package controllers

import models.ShoppingList
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ListAPITest {
    private val listId = Random.nextInt()
    private var tescoShoppingList: ShoppingList? = null
    private var lidlShoppingList: ShoppingList? = null
    private var christmasShoppingList: ShoppingList? = null

    private var populatedShoppingList: ListAPI = ListAPI(JSONSerializer(File("ShoppingLists.json")))
    private var emptyShoppingList: ListAPI = ListAPI(JSONSerializer(File("ShoppingLists.json")))

    @BeforeEach
    fun setup() {
        tescoShoppingList = ShoppingList(Random.nextInt(), "tescoList", "John")
        lidlShoppingList = ShoppingList(Random.nextInt(), "lidlShopping", "Paul")
        christmasShoppingList = ShoppingList(Random.nextInt(), "ShoppingforToys", "Katie")
// add lists to the api

        populatedShoppingList!!.add(tescoShoppingList!!)
        populatedShoppingList!!.add(lidlShoppingList!!)
        populatedShoppingList!!.add(christmasShoppingList!!)
    }
    @AfterEach
    fun ending() {
        tescoShoppingList = null
        lidlShoppingList = null
        christmasShoppingList = null
    }

    @Nested
    inner class AddShoppingList {
        @Test
        fun `adding shopping list to empty list should succeed`() {
            val listToAdd = ShoppingList(listId, "newList", "Jane")
            val result = emptyShoppingList.add(listToAdd)
            assertTrue(result)
        }

        @Test
        fun `adding shopping list to populated list should succeed`() {
            val listToAdd = ShoppingList(listId, "newList", "Jane")
            val result = populatedShoppingList.add(listToAdd)
            assertTrue(result)
        }

        @Test
        fun `adding shopping list with same ID as existing list should fail`() {
            val listToAdd = ShoppingList(tescoShoppingList!!.listId, "newList", "Jane")
            val result = populatedShoppingList.add(listToAdd)
            assertFalse(result)
        }
    }

    @Nested
    inner class RemoveShoppingList {
        @Test
        fun `removing existing list should succeed`() {
            val result = populatedShoppingList.removeList(tescoShoppingList!!)
            assertTrue(result)
        }

        @Test
        fun `removing non-existing list should fail`() {
            val listToDelete = ShoppingList(Random.nextInt(), "nonExistingList", "Tom")
            val result = populatedShoppingList.removeList(listToDelete)
            assertFalse(result)
        }
    }

    @Nested
    inner class AmountOfLists {
        @Test
        fun `empty list should have 0 amount of lists`() {
            val result = emptyShoppingList.amountOfLists()
            assertEquals(0, result)
        }

        @Test
        fun `populated list should have correct amount of lists`() {
            val result = populatedShoppingList.amountOfLists()
            assertEquals(3, result)
        }
    }

    @Nested
    inner class ListAllShoppingLists {
        @Test
        fun `empty list should return "No Shopping Lists Stored"`() {
            val result = emptyShoppingList.listAllShoppingLists()
            assertEquals("No Shopping Lists Stored", result)
        }

        @Test
        fun `populated list should return correctly formatted string`() {
            val result = populatedShoppingList.listAllShoppingLists()
            val expected = "0: ${tescoShoppingList}\n1: ${lidlShoppingList}\n2: $christmasShoppingList"
            assertEquals(expected, result)
        }
    }

    @Nested
    inner class FindShoppingListById {
        @Test
        fun `finding existing list should return correct list`() {
            val result = populatedShoppingList.findShoppingListById(tescoShoppingList!!.listId)
            assertEquals(tescoShoppingList, result)
        }

        @Test
        fun `finding non-existing list should return null`() {
            val result = populatedShoppingList.findShoppingListById(Random.nextInt())
            assertNull(result)
        }
    }

    @Nested
    inner class UpdateShoppingList {
        @Test
        fun `updating existing list should succeed`() {
            val updatedList = ShoppingList(tescoShoppingList!!.listId, "updatedList", "Adam")
            val result = populatedShoppingList.updateShoppingList(tescoShoppingList!!.listId, updatedList)
            assertTrue(result)
            val foundList = populatedShoppingList.findShoppingListById(tescoShoppingList!!.listId)
            assertEquals(updatedList.listName, foundList!!.listName)
            assertEquals(updatedList.author, foundList.author)
            assertEquals(updatedList.currentDateTime, foundList.currentDateTime)
        }

        @Test
        fun `updating list with invalid id should fail`() {
            val updatedList = ShoppingList(Random.nextInt(), "updatedList", "Adam")
            val result = populatedShoppingList.updateShoppingList(-1, updatedList)
            assertFalse(result)
        }
    }
}
