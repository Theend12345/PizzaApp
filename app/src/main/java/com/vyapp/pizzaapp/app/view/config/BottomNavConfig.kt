package com.vyapp.pizzaapp.app.view.config

import android.content.Context
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vyapp.pizzaapp.R
import com.vyapp.pizzaapp.app.util.showToastLong

object BottomNavConfig {
    fun navItemClickListeners(bottomNavigationView: BottomNavigationView, context: Context){
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu -> {
                    true
                }
                R.id.user -> {
                    context.getString(R.string.not_yet).showToastLong(context)
                    true
                }
                R.id.cart -> {
                    context.getString(R.string.not_yet).showToastLong(context)
                    true
                }
                else -> {
                    context.getString(R.string.not_yet).showToastLong(context)
                    false
                }
            }
        }
    }
}