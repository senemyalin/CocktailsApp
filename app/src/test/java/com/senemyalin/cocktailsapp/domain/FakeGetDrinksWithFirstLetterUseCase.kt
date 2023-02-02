package com.senemyalin.cocktailsapp.domain

import com.senemyalin.cocktailsapp.apiException
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.drinkEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetDrinksWithFirstLetterUseCase : GetDrinksWithFirstLetterUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(firstLetter: String): Flow<NetworkResponse<List<DrinkEntity>>> =
        flow {
            emit(NetworkResponse.Loading)
            if (showError) {
                emit(NetworkResponse.Error(apiException))
            } else {
                emit(NetworkResponse.Success(drinkEntities))
            }
        }

}