package com.senemyalin.cocktailsapp.data.source

import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.api.DrinkApi
import com.senemyalin.cocktailsapp.data.dto.Drink
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val drinkApi: DrinkApi): RemoteDataSource {

    override suspend fun getDrinksWithFirstLetter(firstLetter: String): NetworkResponse<List<Drink>> =
        try {
            val response = drinkApi.getDrinksWithFirstLetter(firstLetter)
            NetworkResponse.Success(response.drinks)
        }catch (e: Exception){
            NetworkResponse.Error(e)
        }
}