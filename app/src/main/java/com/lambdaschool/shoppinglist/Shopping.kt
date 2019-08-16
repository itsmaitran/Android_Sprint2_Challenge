package com.lambdaschool.shoppinglist

data class Shopping (
    val product: String,
    val imageId: Int,
    var isAdded: Boolean)