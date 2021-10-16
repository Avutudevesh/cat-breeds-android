package com.example.catbreeds.catlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.catbreeds.catlist.repository.CatsListRepository
import com.example.catbreeds.models.CatsBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatsListViewModelImplTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var subject: CatsListViewModelImpl

    @Mock
    private lateinit var repository: CatsListRepository

    @Mock
    private lateinit var httpException: HttpException

    @Mock
    private lateinit var observerState: Observer<CatsListViewModel.Result>

    @Mock
    private lateinit var catsBreed: CatsBreed


    private val stateLiveData: MutableLiveData<CatsListViewModel.Result> = MutableLiveData()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        subject = CatsListViewModelImpl(stateLiveData, repository)
        subject.stateLiveData.observeForever(observerState)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        subject.stateLiveData.removeObserver(observerState)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCatsList - result empty`() {
        runBlockingTest {
            //GIVEN
            given(repository.fetchCatsList(PAGE_NO)).willReturn(emptyList())

            //WHEN
            subject.fetchCatsList()

            //THEN
            thenObserverShouldHaveCorrectState(
                CatsListViewModel.Result.Loading,
                CatsListViewModel.Result.Empty
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCatsList - result error`() {
        runBlockingTest {
            //GIVEN
            given(repository.fetchCatsList(PAGE_NO)).willThrow(httpException)

            //WHEN
            subject.fetchCatsList()

            //THEN
            thenObserverShouldHaveCorrectState(
                CatsListViewModel.Result.Loading,
                CatsListViewModel.Result.Error
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetchCatsList - result success`() {
        runBlockingTest {
            //GIVEN
            given(repository.fetchCatsList(PAGE_NO)).willReturn(listOf(catsBreed))

            //WHEN
            subject.fetchCatsList()

            //THEN
            thenObserverShouldHaveCorrectState(
                CatsListViewModel.Result.Loading, CatsListViewModel.Result.Success(
                    listOf(catsBreed)
                )
            )
        }
    }

    private fun thenObserverShouldHaveCorrectState(vararg states: CatsListViewModel.Result) {
        states.forEach { then(observerState).should().onChanged(it) }
        then(observerState).shouldHaveNoMoreInteractions()
    }

    companion object {
        private const val PAGE_NO = 1
    }
}