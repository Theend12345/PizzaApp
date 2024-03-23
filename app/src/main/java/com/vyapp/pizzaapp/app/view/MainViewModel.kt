package com.vyapp.pizzaapp.app.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vyapp.pizzaapp.data.entity.DishType
import com.vyapp.pizzaapp.domain.model.BannerModel
import com.vyapp.pizzaapp.domain.model.DishModel
import com.vyapp.pizzaapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDishDataByTypeFromDbUsecase: GetDishDataByTypeFromDbUsecase,
    private val fetchDessertResponseUsecase: FetchDessertResponseUsecase,
    private val fetchPizzaResponseUsecase: FetchPizzaResponseUsecase,
    private val saveDishToDbUsecase: SaveDishToDbUsecase,
    private val getBannerDataUsecase: GetBannerDataUsecase
) : ViewModel() {

    private val _dishData: MutableStateFlow<UIState<Any>> =
        MutableStateFlow(UIState.Loading)
    val dishData: StateFlow<UIState<Any>>
        get() = _dishData.asStateFlow()

    private val _bannerData: MutableStateFlow<List<BannerModel>> =
        MutableStateFlow(emptyList())
    val bannerData: StateFlow<List<BannerModel>>
        get() = _bannerData.asStateFlow()

    init {
        getBannerData()
        getPizzaList()
    }

    private fun getBannerData(){
        viewModelScope.launch {
            getBannerDataUsecase.invoke().collect{ bannerList ->
                _bannerData.value = bannerList
            }
        }
    }

    fun getPizzaList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchPizzaResponseUsecase().collectLatest { pizzaList ->
                    _dishData.value = UIState.Success(pizzaList)
                    cacheDishData(pizzaList)
                }
            } catch (e: Throwable) {
                try {
                    getDishDataByTypeFromDbUsecase(DishType.PIZZA.name).collectLatest { pizzaList ->
                        if (pizzaList.isEmpty()) throw IllegalStateException()
                        _dishData.value = UIState.Success(pizzaList)
                    }
                } catch (e: Throwable) {
                    _dishData.value = UIState.Error(e)
                }
            }
        }
    }

    fun getDessertsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchDessertResponseUsecase().collectLatest { dessertList ->
                    _dishData.value = UIState.Success(dessertList)
                    cacheDishData(dessertList)
                }
            } catch (e: Throwable) {
                try {
                    getDishDataByTypeFromDbUsecase(DishType.DESSERT.name).collectLatest { dessertList ->
                        if (dessertList.isEmpty()) throw IllegalStateException()
                        _dishData.value = UIState.Success(dessertList)
                    }
                } catch (e: Throwable) {
                    _dishData.value = UIState.Error(e)
                }
            }
        }
    }

    private fun cacheDishData(dishList: List<DishModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            if (dishList.isNotEmpty()) {
                for (dish in dishList) {
                    saveDishToDbUsecase(dish)
                }
            }
        }
    }
}