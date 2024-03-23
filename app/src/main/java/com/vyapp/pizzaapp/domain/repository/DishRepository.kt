package com.vyapp.pizzaapp.domain.repository

import com.vyapp.pizzaapp.domain.model.BannerModel
import com.vyapp.pizzaapp.domain.model.DishModel
import kotlinx.coroutines.flow.Flow

interface DishRepository {
    suspend fun getAllPizzaDataResponse(): Flow<List<DishModel>>
    suspend fun getAllDessertsDataResponse(): Flow<List<DishModel>>
    fun getAllDishDataByTypeFrom(type: String): Flow<List<DishModel>>
    fun saveDish(dish: DishModel)
    fun getBannerData(): Flow<List<BannerModel>>
}