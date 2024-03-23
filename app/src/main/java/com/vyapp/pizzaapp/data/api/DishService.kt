package com.vyapp.pizzaapp.data.api

import com.vyapp.pizzaapp.data.entity.DessertResponse
import com.vyapp.pizzaapp.data.entity.PizzaResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface DishService {
    @Headers(
        "X-RapidAPI-Key: d22be8c9bfmsh8e93618a923cadcp191f00jsn14bf325041e7",
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
    )
    @GET(ALL_PIZZA_DATA)
    suspend fun fetchAllPizzaData(): List<PizzaResponse>

    @Headers(
        "X-RapidAPI-Key: d22be8c9bfmsh8e93618a923cadcp191f00jsn14bf325041e7",
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
    )
    @GET(ALL_DESSERTS_DATA)
    suspend fun fetchAllDessertData(): List<DessertResponse>
}