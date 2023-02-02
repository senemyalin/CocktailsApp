package com.senemyalin.cocktailsapp

import com.google.common.annotations.VisibleForTesting
import com.google.gson.annotations.SerializedName
import com.senemyalin.cocktailsapp.data.dto.Drink
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import com.senemyalin.cocktailsapp.ui.home.HomeUiData

const val SAMPLE_FIRST_LETTER = "f"
const val SAMPLE_RESPONSE_FILE_NAME = "CocktailResponse.json"

@VisibleForTesting
val drinkDto = Drink(
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null
)

@VisibleForTesting
val cocktailList = mutableListOf(drinkDto)

val ingredients : ArrayList<String?> = ArrayList()

val drinkEntity = DrinkEntity(
    "",
    "",
    "",
    "",
    ingredients
)

val drinkEntities = mutableListOf(drinkEntity)

val homeUiData = HomeUiData(
    "",
    ingredients,
    "",
    "",
)

val homeUiDataList = mutableListOf(homeUiData)

val apiException = Exception("Something went wrong")