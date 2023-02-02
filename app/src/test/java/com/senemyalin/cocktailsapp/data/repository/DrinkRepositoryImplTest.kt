package com.senemyalin.cocktailsapp.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.senemyalin.cocktailsapp.SAMPLE_FIRST_LETTER
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.source.FakeRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class DrinkRepositoryImplTest {

    private val drinkListMapperImpl = DrinkDomainListMapperImpl()
    private lateinit var drinkRepositoryImpl: DrinkRepositoryImpl
    private val fakeRemoteDataSource = FakeRemoteDataSource()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        drinkRepositoryImpl = DrinkRepositoryImpl(fakeRemoteDataSource, drinkListMapperImpl)
    }

    @Test
    fun when_dataSourceResponseSuccess_is_state_success() {
        runBlocking {
            drinkRepositoryImpl.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponse.Success::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun when_dataSourceResponseFailure_is_state_failure() {
        fakeRemoteDataSource.updateShowError(true)
        runBlocking {
            drinkRepositoryImpl.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER).test {
                Truth.assertThat(awaitItem()).isInstanceOf(NetworkResponse.Error::class.java)
                awaitComplete()
            }
        }
    }
}