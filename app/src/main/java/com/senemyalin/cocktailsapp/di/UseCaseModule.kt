package com.senemyalin.cocktailsapp.di

import com.senemyalin.cocktailsapp.domain.GetDrinksWithFirstLetterUseCase
import com.senemyalin.cocktailsapp.domain.GetDrinksWithFirstLetterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDrinkWithFirstLetterUseCase(getDrinksWithFirstLetterUseCaseImpl: GetDrinksWithFirstLetterUseCaseImpl): GetDrinksWithFirstLetterUseCase
}