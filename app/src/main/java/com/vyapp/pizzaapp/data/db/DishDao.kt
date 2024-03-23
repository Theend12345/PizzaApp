package com.vyapp.pizzaapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vyapp.pizzaapp.data.entity.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Query("SELECT * FROM dish WHERE type=:type")
    fun getAllDishDataByType(type: String): Flow<List<DishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDish(pizzaEntity: DishEntity)

}