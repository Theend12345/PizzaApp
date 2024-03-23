package com.vyapp.pizzaapp.app.util

import android.content.Context
import com.vyapp.pizzaapp.data.api.BannerFakeApi
import com.vyapp.pizzaapp.data.entity.*
import com.vyapp.pizzaapp.domain.model.BannerModel
import com.vyapp.pizzaapp.domain.model.DishModel

fun PizzaResponse.toDishModel() = DishModel(description, name, price, img, DishType.PIZZA.name)
fun DessertResponse.toDishModel() = DishModel(description, name, price, img, DishType.DESSERT.name)
fun DishEntity.toDishModel() = DishModel(description, name, price, null, type)
fun DishModel.toDishEntity() = DishEntity(name, description, price, type)
fun BannerResponse.toBannerModel() = BannerModel(img, link)
fun Int.toDp(context: Context) = (this * context.resources.displayMetrics.density).toInt()
