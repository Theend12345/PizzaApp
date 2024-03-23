package com.vyapp.pizzaapp.app.view.config

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout
import com.vyapp.pizzaapp.R
import com.vyapp.pizzaapp.app.util.showToastLong
import com.vyapp.pizzaapp.app.view.MainViewModel

private const val PIZZA = 0
private const val COMBO = 1
private const val DESSERT = 2
private const val DRINK = 3

object TabLayoutConfig {

    fun tabLayoutConfig(tabLayout: TabLayout) {
        val tabs = tabLayout.getChildAt(0) as ViewGroup
        for (_tab in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(_tab)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 0f
            layoutParams.marginEnd = 16
            layoutParams.marginStart = 16
            tab.layoutParams = layoutParams
            tabLayout.requestLayout()
        }
    }

    fun tabClickListeners(tabLayout: TabLayout, mainViewModel: MainViewModel, context: Context) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    PIZZA -> {
                        mainViewModel.getPizzaList()
                    }
                    COMBO -> {
                        context.getString(R.string.not_yet).showToastLong(context)
                    }
                    DESSERT -> {
                        mainViewModel.getDessertsList()
                    }
                    DRINK -> {
                        context.getString(R.string.not_yet).showToastLong(context)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }
        })
    }

}