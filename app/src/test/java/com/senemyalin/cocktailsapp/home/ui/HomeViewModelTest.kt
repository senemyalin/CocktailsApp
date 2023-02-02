package com.senemyalin.cocktailsapp.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.senemyalin.cocktailsapp.SAMPLE_FIRST_LETTER
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.domain.FakeGetDrinksWithFirstLetterUseCase
import com.senemyalin.cocktailsapp.drinkEntities
import com.senemyalin.cocktailsapp.homeUiDataList
import com.senemyalin.cocktailsapp.ui.home.DrinkHomeUiMapperImpl
import com.senemyalin.cocktailsapp.ui.home.HomeUiState
import com.senemyalin.cocktailsapp.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class HomeViewModelTest {

    @Mock
    private lateinit var fakeGetDrinksWithFirstLetterUseCase: FakeGetDrinksWithFirstLetterUseCase

    private val drinkHomeUiMapperImpl = DrinkHomeUiMapperImpl()

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var observer: Observer<HomeUiState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = HomeViewModel(fakeGetDrinksWithFirstLetterUseCase, drinkHomeUiMapperImpl)
        viewModel.drinkHomeUiState.observeForever(observer)
    }

    @Test
    fun when_useCaseReturnedDownloading_is_live_data_state_downloading_and_success() {
        runBlocking {
            Mockito.`when`(fakeGetDrinksWithFirstLetterUseCase.invoke(SAMPLE_FIRST_LETTER))
                .thenReturn(flow {
                    emit(NetworkResponse.Loading)
                    emit(NetworkResponse.Success(drinkEntities))
                })

            viewModel.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)

            verify(observer).onChanged(HomeUiState.Loading)
            verify(observer).onChanged(HomeUiState.Success(homeUiDataList))
        }
    }

//    @Test
//    fun when_useCaseReturnedDownloading_is_live_data_state_downloading_and_error(){
//        fakeGetDrinksWithFirstLetterUseCase.updateShowError(true)
//        runBlocking {
//            Mockito.`when`(fakeGetDrinksWithFirstLetterUseCase.invoke(SAMPLE_FIRST_LETTER))
//                .thenReturn(flow{
//                    emit(NetworkResponse.Loading)
//                    emit(NetworkResponse.Error())
//                })
//
//            viewModel.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
//
//            verify(observer).onChanged(HomeUiState.Loading)
//            verify(observer).onChanged(HomeUiState.Error())
//        }
//    }

    @After
    fun shutDown() {
        Dispatchers.resetMain()
        viewModel.drinkHomeUiState.removeObserver(observer)
    }
}