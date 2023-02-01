package com.senemyalin.cocktailsapp.data.repository

import com.senemyalin.cocktailsapp.data.dto.Drink
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import com.senemyalin.cocktailsapp.common.DrinkListMapper
import javax.inject.Inject

class DrinkDomainListMapperImpl @Inject constructor() : DrinkListMapper<Drink, DrinkEntity> {

    override fun map(input: List<Drink>?): List<DrinkEntity> {
        val a = arrayListOf<String?>()

        return input?.map {
            a.clear()
            a.add(it.strIngredient1.orEmpty())
            a.add(it.strIngredient2.orEmpty())
            a.add(it.strIngredient3.orEmpty())
            a.add(it.strIngredient4.orEmpty())
            DrinkEntity(
                name = it.strDrink.orEmpty(),
                category = it.strCategory.orEmpty(),
                videoUrl = it.strVideo.orEmpty(),
                imageUrl = it.strDrinkThumb.orEmpty(),
                ingredients = a,
            )
        } ?: emptyList()
    }
}