package com.vyapp.pizzaapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vyapp.pizzaapp.data.entity.DishEntity

const val DB_NAME = "dish-db"

@Database(entities = [DishEntity::class], version = 1)
abstract class DishDatabase: RoomDatabase() {
    abstract fun dishDao(): DishDao
}