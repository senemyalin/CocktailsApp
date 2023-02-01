package com.senemyalin.cocktailsapp.data.api

import com.senemyalin.cocktailsapp.data.dto.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("search.php")
    suspend fun getDrinksWithFirstLetter(@Query("f") firstLetter: String): DrinkResponse
}