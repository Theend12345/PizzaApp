package com.vyapp.pizzaapp.data.api

import com.vyapp.pizzaapp.R
import com.vyapp.pizzaapp.data.entity.BannerResponse

object BannerFakeApi {
    val banners: List<BannerResponse> = listOf(
        BannerResponse(R.drawable.banner1, "https://www.youtube.com/watch?v=dQw4w9WgXcQ"),
        BannerResponse(R.drawable.banner2, "https://www.youtube.com/watch?v=dQw4w9WgXcQ")
    )
}