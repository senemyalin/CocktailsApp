package com.senemyalin.cocktailsapp.data.source

import com.google.common.truth.Truth.assertThat
import com.senemyalin.cocktailsapp.SAMPLE_FIRST_LETTER
import com.senemyalin.cocktailsapp.cocktailList
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.api.DrinkApi
import com.senemyalin.cocktailsapp.data.dto.DrinkResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RemoteDataSourceImplTest {

    @Mock
    private lateinit var drinkApi: DrinkApi

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(drinkApi)
    }

    @Test
    fun `when cocktails list returned is state success`() {
        runBlocking {
            Mockito.`when`(drinkApi.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER))
                .thenReturn(DrinkResponse(cocktailList))

            val response = remoteDataSourceImpl.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
            assertThat(response).isInstanceOf(NetworkResponse.Success::class.java)
        }
    }

    @Test
    fun `when cocktails list returned is state failure`() {
        runBlocking {
            Mockito.`when`(drinkApi.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER))
                .thenReturn(null)

            val response = remoteDataSourceImpl.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
            assertThat(response).isInstanceOf(NetworkResponse.Error::class.java)
        }
    }
}