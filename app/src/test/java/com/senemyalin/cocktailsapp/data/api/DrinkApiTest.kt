package com.senemyalin.cocktailsapp.data.api

import com.senemyalin.cocktailsapp.SAMPLE_FIRST_LETTER
import com.senemyalin.cocktailsapp.SAMPLE_RESPONSE_FILE_NAME
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat
import org.junit.After

class DrinkApiTest {

    private lateinit var api: DrinkApi

    private val mockWebServer = MockWebServer()

    @Before
    fun setup(){
        mockWebServer.start(8080)
        api = Retrofit.Builder().baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinkApi::class.java)
    }

    @Test
    fun when_searchQueryF_is_response_notNull(){
        runBlocking {
            enqueueResponse(SAMPLE_RESPONSE_FILE_NAME)
            val response = api.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
            val request = mockWebServer.takeRequest()
            assertThat(response).isNotNull()
        }
    }

    @Test
    fun when_searchQueryF_is_requestPath_same() {
        runBlocking {
            enqueueResponse(SAMPLE_RESPONSE_FILE_NAME)
            val response = api.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
            val request = mockWebServer.takeRequest()
            assertThat(request.path).isEqualTo("/search.php?f=$SAMPLE_FIRST_LETTER")
        }
    }

    @Test
    fun when_searchQuery10_is_firstItemId_expected() {
        runBlocking {
            enqueueResponse(SAMPLE_RESPONSE_FILE_NAME)
            val response = api.getDrinksWithFirstLetter(SAMPLE_FIRST_LETTER)
            val firstItem = response.drinks!!.first()
            assertThat(firstItem.idDrink).isEqualTo("178352")
        }
    }

    private fun enqueueResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }
}