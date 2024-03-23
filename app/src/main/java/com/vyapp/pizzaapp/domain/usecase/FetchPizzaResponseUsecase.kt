package com.vyapp.pizzaapp.domain.usecase

import com.vyapp.pizzaapp.domain.model.DishModel
import com.vyapp.pizzaapp.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPizzaResponseUsecase @Inject constructor(private val repository: DishRepository) {
    suspend operator fun invoke(): Flow<List<DishModel>> = repository.getAllPizzaDataResponse()
}