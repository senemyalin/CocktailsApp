package com.senemyalin.cocktailsapp.data.repository

import com.senemyalin.cocktailsapp.common.DrinkListMapper
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.dto.Drink
import com.senemyalin.cocktailsapp.data.source.RemoteDataSource
import com.senemyalin.cocktailsapp.di.IoDispatcher
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val drinkListMapper: DrinkListMapper<Drink, DrinkEntity>

) :
    DrinkRepository {

    override fun getDrinksWithFirstLetter(firstLetter: String): Flow<NetworkResponse<List<DrinkEntity>>> =
        flow {
            try {
                when (val response = remoteDataSource.getDrinksWithFirstLetter(firstLetter)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> TODO()
                    is NetworkResponse.Success -> {
                        val mappedResponse = drinkListMapper.map(response.result)
                        emit(
                            NetworkResponse.Success(
                                mappedResponse
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

}