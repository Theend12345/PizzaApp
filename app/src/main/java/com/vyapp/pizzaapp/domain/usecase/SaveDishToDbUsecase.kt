package com.vyapp.pizzaapp.domain.usecase

import com.vyapp.pizzaapp.domain.model.DishModel
import com.vyapp.pizzaapp.domain.repository.DishRepository
import javax.inject.Inject

class SaveDishToDbUsecase @Inject constructor(private val repository: DishRepository) {
    operator fun invoke(dish: DishModel) {
        repository.saveDish(dish)
    }
}