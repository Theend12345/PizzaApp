package com.vyapp.pizzaapp.domain.usecase

import com.vyapp.pizzaapp.domain.model.BannerModel
import com.vyapp.pizzaapp.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannerDataUsecase @Inject constructor(private val repository: DishRepository) {
    operator fun invoke(): Flow<List<BannerModel>> = repository.getBannerData()
}