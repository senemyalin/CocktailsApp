package com.senemyalin.cocktailsapp.data.repository

import com.senemyalin.cocktailsapp.domain.DrinkEntity
import com.senemyalin.cocktailsapp.common.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface DrinkRepository {

    fun getDrinksWithFirstLetter(firstLetter: String): Flow<NetworkResponse<List<DrinkEntity>>>

}