package com.vyapp.pizzaapp.app.view.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vyapp.pizzaapp.databinding.DishItemBinding
import com.vyapp.pizzaapp.domain.model.DishModel


class DishAdapter(private val fingers: List<DishModel>) :
    RecyclerView.Adapter<DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = DishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val finger = fingers[position]
        holder.bind(finger)
    }

    override fun getItemCount(): Int = fingers.size

}