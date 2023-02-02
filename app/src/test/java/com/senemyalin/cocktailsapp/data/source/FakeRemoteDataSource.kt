package com.senemyalin.cocktailsapp.data.source

import com.senemyalin.cocktailsapp.apiException
import com.senemyalin.cocktailsapp.cocktailList
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.dto.Drink

class FakeRemoteDataSource : RemoteDataSource {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override suspend fun getDrinksWithFirstLetter(firstLetter: String): NetworkResponse<List<Drink>> =
        try {
            NetworkResponse.Loading
            if (showError) {
                NetworkResponse.Error(apiException)
            } else {
                NetworkResponse.Success(cocktailList)
            }
        } finally {

        }
}