package controllers
import models.List
import java.util.*
import kotlin.collections.ArrayList

class ListAPI {


        private val shoppingLists = mutableMapOf<String, MutableList<List>>()
        private val scanner = Scanner(System.`in`)

       private val lists = ArrayList<List>()

        fun add(list: List): Boolean {
            return lists.add(list)
        }

        fun addItemToList(){

        }

}


