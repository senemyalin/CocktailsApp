package com.senemyalin.cocktailsapp.di

import com.senemyalin.cocktailsapp.data.api.DrinkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object ProductNetworkModule {

    @Provides
    @ViewModelScoped
    fun provideShoppingApi(): DrinkApi {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinkApi::class.java)
    }
}