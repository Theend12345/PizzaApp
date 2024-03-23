package com.vyapp.pizzaapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class DishEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val description: String,
    val price: Int,
    val type: String
)