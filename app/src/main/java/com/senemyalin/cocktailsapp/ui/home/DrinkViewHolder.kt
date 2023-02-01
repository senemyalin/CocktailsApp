package com.senemyalin.cocktailsapp.ui.home

import android.view.ViewGroup
import com.senemyalin.cocktailsapp.databinding.AdapterDrinkItemBinding
import com.senemyalin.cocktailsapp.ui.base.BaseViewHolder
import com.senemyalin.cocktailsapp.utility.inflateAdapterItem

class DrinkViewHolder(private val binding: AdapterDrinkItemBinding) :
    BaseViewHolder<HomeUiData>(binding.root) {

    companion object {
        fun createFrom(parent: ViewGroup) =
            DrinkViewHolder(parent.inflateAdapterItem(AdapterDrinkItemBinding::inflate))
    }

    override fun onBind(data: HomeUiData) {
        binding.drinkComponent.setDrinkData(data)
    }
}