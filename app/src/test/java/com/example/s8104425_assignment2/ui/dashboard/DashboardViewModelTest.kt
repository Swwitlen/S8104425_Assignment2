package com.example.s8104425_assignment2.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.s8104425_assignment2.model.DashboardResponse
import com.example.s8104425_assignment2.model.SportEntity
import com.example.s8104425_assignment2.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.*
import org.mockito.kotlin.*
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    //makes livedata execute immediate
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // LiveData runs synchronously

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: MainRepository
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        repository = mock() // Mockito mock of repository
        Dispatchers.setMain(testDispatcher) // Override Main dispatcher for testing
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() //reset main dispatcher
    }

    //test successful fetchDashboard
    @Test
    fun `fetchDashboard success updates dashboardLiveData`() = runTest {
        val entities = listOf(
            SportEntity("Football", 11, "Outdoor", true, "Popular sport"),
            SportEntity("Basketball", 5, "Indoor", true, "Indoor court")
        )
        val response = DashboardResponse(entities, entities.size)

        whenever(repository.getDashboard("validKeypass"))
            .thenReturn(Response.success(response))

        viewModel.fetchDashboard("validKeypass")
        testDispatcher.scheduler.advanceUntilIdle() // wait for coroutine to finish

        val result = viewModel.dashboardLiveData.value
        assertEquals(2, result?.size)
        assertEquals("Football", result?.get(0)?.sportName)
        assertEquals("Basketball", result?.get(1)?.sportName)
    }

    @Test
    fun `fetchDashboard failure updates errorLiveData`() = runTest {
        whenever(repository.getDashboard("invalidKeypass"))
            .thenReturn(Response.error(401, "".toResponseBody(null))) //mock success

        viewModel.fetchDashboard("invalidKeypass")
        testDispatcher.scheduler.advanceUntilIdle() //wait

        val error = viewModel.errorLiveData.value
        assertTrue(error?.contains("Failed to fetch dashboard") == true)
    }

    //test failure case
    @Test
    fun `fetchDashboard exception updates errorLiveData`() = runTest {
        whenever(repository.getDashboard("keypass"))
            .thenAnswer { throw Exception("Network error") }

        viewModel.fetchDashboard("keypass")
        testDispatcher.scheduler.advanceUntilIdle()

        val error = viewModel.errorLiveData.value
        assertTrue(error?.contains("Network error") == true)
    }
}
