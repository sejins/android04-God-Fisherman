package com.android04.godfisherman.presentation.home

import android.util.Log
import com.android04.godfisherman.common.Result
import com.android04.godfisherman.common.constant.FishRankingRequest
import com.android04.godfisherman.data.repository.HomeRepository
import com.android04.godfisherman.data.repository.LocationRepository
import com.android04.godfisherman.data.repository.LogInRepository
import com.android04.godfisherman.presentation.InstantTaskExecutorRule
import com.android04.godfisherman.presentation.getOrAwaitValue
import com.android04.godfisherman.presentation.rankingdetail.RankingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock lateinit var homeRepository: HomeRepository
    @Mock private lateinit var loginRepository: LogInRepository
    @Mock private lateinit var locationRepository: LocationRepository
    private lateinit var homeViewModel: HomeViewModel
    val testDispatcher = TestCoroutineDispatcher()
    @get:Rule var instantExecutor = InstantTaskExecutorRule()
    val mainThreadSurrogate = newSingleThreadContext(HomeViewModelTest::class.java.simpleName)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
//        Dispatchers.setMain(TestCoroutineDispatcher())
//        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        homeViewModel = HomeViewModel(homeRepository, loginRepository, locationRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @DisplayName("랭킹 받아오기 실패")
    @Test
    fun fetchRanking_fail() {
        // Given
        val isFresh = false
        val rankingType = FishRankingRequest.HOME

        // When
        testDispatcher.runBlockingTest {
            `when`(homeRepository.fetchRankingList(rankingType, isFresh)).thenReturn(
                Result.Fail("실패")
            )
        }

        homeViewModel.fetchRanking(isFresh)

        // Then
        homeViewModel.error.value?.let { result ->
            assertEquals("실패", result.peekContent())
        }
    }
}
