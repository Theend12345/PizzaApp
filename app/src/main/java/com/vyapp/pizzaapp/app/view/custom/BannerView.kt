package com.vyapp.pizzaapp.app.view.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import com.vyapp.pizzaapp.app.util.toDp
import com.vyapp.pizzaapp.domain.model.BannerModel

@SuppressLint("ViewConstructor")
class BannerView(context: Context, private val bannerModel: BannerModel) :
    AppCompatImageView(context) {

    var bannerWidth = 300
    var bannerHeight = 113

    init {
        setOnClickListener {
            startActivity(
                this.context,
                Intent(Intent.ACTION_VIEW, Uri.parse(bannerModel.link)),
                null
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        layoutParams.width = bannerWidth.toDp(context)
        layoutParams.height = bannerHeight.toDp(context)
        scaleType = ScaleType.CENTER_CROP
        setImageResource(bannerModel.img)
        isClickable = true
    }

}