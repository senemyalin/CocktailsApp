package com.senemyalin.cocktailsapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.senemyalin.cocktailsapp.databinding.LayoutDrinkBinding
import com.senemyalin.cocktailsapp.ui.home.HomeUiData
import com.senemyalin.cocktailsapp.utility.loadImage

//sadece contextli yapi, attribute setli hali, ve defstyleattrli halini de olusturur verirsen
class DrinkUiComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding = LayoutDrinkBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun setDrinkData(homeUiData: HomeUiData) {
        binding.drinkName.text = homeUiData.name
        binding.drinkImage.loadImage(homeUiData.imageUrl)
    }
}