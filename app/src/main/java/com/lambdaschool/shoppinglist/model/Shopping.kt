package com.lambdaschool.shoppinglist.model

data class Shopping (
    val product: String,
    val imageId: Int,
    var isAdded: Boolean)