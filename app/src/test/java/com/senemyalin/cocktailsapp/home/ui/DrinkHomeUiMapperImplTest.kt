package com.senemyalin.cocktailsapp.home.ui

import com.google.common.truth.Truth.assertThat
import com.senemyalin.cocktailsapp.drinkEntities
import com.senemyalin.cocktailsapp.ui.home.DrinkHomeUiMapperImpl
import com.senemyalin.cocktailsapp.ui.home.HomeUiData
import org.junit.Before
import org.junit.Test

class DrinkHomeUiMapperImplTest {

    private val drinkHomeUiMapperImpl = DrinkHomeUiMapperImpl()
    private lateinit var uiElements: List<HomeUiData>

    @Before
    fun setup() {
        uiElements = drinkHomeUiMapperImpl.map(drinkEntities)
    }

    @Test
    fun when_entity_mapped_is_name_correct() {
        assertThat(uiElements[0].name).isEqualTo(drinkEntities[0].name)
    }

    @Test
    fun when_entity_mapped_is_ingredients_correct() {
        assertThat(uiElements[0].ingredients).isEqualTo(drinkEntities[0].ingredients)
    }

    @Test
    fun when_entity_mapped_is_imageUrl_correct() {
        assertThat(uiElements[0].imageUrl).isEqualTo(drinkEntities[0].imageUrl)
    }

    @Test
    fun when_entity_mapped_is_videoUrl_correct() {
        assertThat(uiElements[0].videoUrl).isEqualTo(drinkEntities[0].videoUrl)
    }

}