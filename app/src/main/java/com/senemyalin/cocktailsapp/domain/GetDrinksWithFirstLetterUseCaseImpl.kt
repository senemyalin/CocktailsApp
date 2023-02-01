package com.senemyalin.cocktailsapp.domain

import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDrinksWithFirstLetterUseCaseImpl @Inject constructor(
    private val drinkRepository: DrinkRepository,
) : GetDrinksWithFirstLetterUseCase {

    override operator fun invoke(firstLetter: String): Flow<NetworkResponse<List<DrinkEntity>>> =
        drinkRepository.getDrinksWithFirstLetter(firstLetter)
}