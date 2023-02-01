package com.senemyalin.cocktailsapp.data.source

import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.dto.Drink

interface RemoteDataSource{

    suspend fun getDrinksWithFirstLetter(firstLetter: String): NetworkResponse<List<Drink>>

}