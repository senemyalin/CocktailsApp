package com.senemyalin.cocktailsapp.domain

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.senemyalin.cocktailsapp.SAMPLE_FIRST_LETTER
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.data.repository.FakeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDrinksWithFirstLetterUseCaseImplTest {

    private val fakeRepository = FakeRepository()

    private lateinit var getDrinksWithFirstLetterUseCaseImpl: GetDrinksWithFirstLetterUseCaseImpl

    @Before
    fun setup() {
        getDrinksWithFirstLetterUseCaseImpl = GetDrinksWithFirstLetterUseCaseImpl(fakeRepository)
    }

    @Test
    fun when_searchQueryF_isFirstState_Downloading() {
        runBlocking {
            getDrinksWithFirstLetterUseCaseImpl.invoke(SAMPLE_FIRST_LETTER).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponse.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun when_repositoryResponseFailure_is_state_downloading_and_failure_sequentially() {
        fakeRepository.updateShowError(true)
        runBlocking {
            getDrinksWithFirstLetterUseCaseImpl.invoke(SAMPLE_FIRST_LETTER).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponse.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponse.Error::class.java)
                awaitComplete()
            }
        }
    }

    @Test
    fun when_repositoryResponseSuccess_is_state_downloading_and_success_sequentially() {
        runBlocking {
            getDrinksWithFirstLetterUseCaseImpl.invoke(SAMPLE_FIRST_LETTER).test {
                assertThat(awaitItem()).isInstanceOf(NetworkResponse.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(NetworkResponse.Success::class.java)
                awaitComplete()
            }
        }
    }
}