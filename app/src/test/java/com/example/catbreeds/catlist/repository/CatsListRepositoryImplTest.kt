package com.example.catbreeds.catlist.repository

import com.example.catbreeds.network.CatsApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatsListRepositoryImplTest {

    private lateinit var subject: CatsListRepositoryImpl

    @Mock
    private lateinit var mockCatsApiService: CatsApiService

    @Before
    fun setUp() {
        subject = CatsListRepositoryImpl(mockCatsApiService)
    }

    @Test
    fun testFetchCatsList() {
        runBlocking {
            //WHEN
            subject.fetchCatsList(PAGE_NUMBER)

            //THEN
            then(mockCatsApiService).should().getCatsList(2)
        }
    }

    companion object {
        private const val PAGE_NUMBER = 1
    }


}