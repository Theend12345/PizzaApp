package com.vyapp.pizzaapp.app.view.rv

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vyapp.pizzaapp.app.util.priceStringFormat
import com.vyapp.pizzaapp.databinding.DishItemBinding
import com.vyapp.pizzaapp.domain.model.DishModel


class DishViewHolder(private val binding: DishItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dishModel: DishModel) {
        with(binding) {
            dishName.text = dishModel.name
            dishDescription.text = dishModel.description
            dishPrice.text = priceStringFormat(dishModel.price, binding.root.context)
            if (dishModel.img != null) {
                Glide.with(itemView.context).load(dishModel.img).into(dishImg)
            }
        }
    }
}