package com.senemyalin.cocktailsapp.ui.home

import com.senemyalin.cocktailsapp.common.DrinkListMapper
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import javax.inject.Inject

class DrinkHomeUiMapperImpl @Inject constructor() : DrinkListMapper<DrinkEntity, HomeUiData> {

    override fun map(input: List<DrinkEntity>?): List<HomeUiData> {

        return input?.map {
            HomeUiData(it.name, it.ingredients, it.videoUrl, it.imageUrl)
        } ?: emptyList()
    }
}