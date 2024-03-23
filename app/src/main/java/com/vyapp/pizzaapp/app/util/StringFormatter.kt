package com.vyapp.pizzaapp.app.util

import android.content.Context
import android.widget.Toast
import com.vyapp.pizzaapp.R

fun priceStringFormat(price: Int, context: Context) =
    "${context.getString(R.string.from)} $price ${context.getString(R.string.dollar)}"

fun String.showToastLong(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()
}