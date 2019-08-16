package com.lambdaschool.shoppinglist

import com.lambdaschool.sprint2_challenge.itemIds
import com.lambdaschool.sprint2_challenge.itemNames

class ShoppingRepository {
    companion object {
        val shoppingList = mutableListOf<Shopping>()
        fun createShoppingList() {
            for (i in 0 until itemNames.size) {
                shoppingList.add(Shopping(itemNames[i], itemIds[i], isAdded = false))
            }
        }
    }
}