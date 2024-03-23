package com.vyapp.pizzaapp.domain.usecase

import com.vyapp.pizzaapp.domain.model.DishModel
import com.vyapp.pizzaapp.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDishDataByTypeFromDbUsecase @Inject constructor(private val repository: DishRepository) {
    operator fun invoke(type: String): Flow<List<DishModel>> = repository.getAllDishDataByTypeFrom(type)
}