package com.senemyalin.cocktailsapp.domain

import com.senemyalin.cocktailsapp.common.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface GetDrinksWithFirstLetterUseCase {

    operator fun invoke(firstLetter: String): Flow<NetworkResponse<List<DrinkEntity>>>
}