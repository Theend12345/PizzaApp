package com.vyapp.pizzaapp.data.repository

import com.vyapp.pizzaapp.app.util.toBannerModel
import com.vyapp.pizzaapp.app.util.toDishEntity
import com.vyapp.pizzaapp.app.util.toDishModel
import com.vyapp.pizzaapp.data.api.BannerFakeApi
import com.vyapp.pizzaapp.data.api.DishService
import com.vyapp.pizzaapp.data.db.DishDatabase
import com.vyapp.pizzaapp.domain.model.BannerModel
import com.vyapp.pizzaapp.domain.model.DishModel
import com.vyapp.pizzaapp.domain.repository.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DishRepositoryImp(
    private val dishService: DishService,
    dishDatabase: DishDatabase
) : DishRepository {

    private val db = dishDatabase.dishDao()

    override suspend fun getAllPizzaDataResponse(): Flow<List<DishModel>> =
        withContext(Dispatchers.IO) {
            flowOf(dishService.fetchAllPizzaData().map { it.toDishModel() })
        }

    override suspend fun getAllDessertsDataResponse(): Flow<List<DishModel>> =
        withContext(Dispatchers.IO) {
            flowOf(dishService.fetchAllDessertData().map { it.toDishModel() })
        }

    override fun getAllDishDataByTypeFrom(type: String): Flow<List<DishModel>> =
        db.getAllDishDataByType(type).map { pizzaList ->
            pizzaList.map { it.toDishModel() }
        }

    override fun saveDish(dish: DishModel) {
        db.saveDish(dish.toDishEntity())
    }

    override fun getBannerData(): Flow<List<BannerModel>> = flowOf(BannerFakeApi.banners.map { bannerList ->
        bannerList.toBannerModel()
    })

}