package com.vyapp.pizzaapp.domain.model

data class DishModel(
    val description: String,
    val name: String,
    val price: Int,
    val img: String?,
    val type: String
)
