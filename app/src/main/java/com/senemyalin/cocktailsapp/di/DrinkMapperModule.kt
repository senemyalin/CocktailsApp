package com.senemyalin.cocktailsapp.di

import com.senemyalin.cocktailsapp.common.DrinkListMapper
import com.senemyalin.cocktailsapp.data.dto.Drink
import com.senemyalin.cocktailsapp.data.repository.DrinkDomainListMapperImpl
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import com.senemyalin.cocktailsapp.ui.home.DrinkHomeUiMapperImpl
import com.senemyalin.cocktailsapp.ui.home.HomeUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DrinkMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDrinkDomainListMapper(drinkDomainListMapperImpl: DrinkDomainListMapperImpl): DrinkListMapper<Drink, DrinkEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindDrinkHomeUiMapper(drinkHomeUiMapperImpl: DrinkHomeUiMapperImpl): DrinkListMapper<DrinkEntity, HomeUiData>

}