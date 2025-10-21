package com.example.s8104425_assignment2.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.s8104425_assignment2.model.AuthResponse
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
class LoginViewModelTest {

    // Ensures LiveData updates instantly
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() //ensure liveData posts updates instantly for unit testing

    private val testDispatcher = StandardTestDispatcher() //allows controlling coroutine execution in tests
    private lateinit var repository: MainRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        // Mock the repository
        repository = mock() // use mockito to create a fake repository
        // Override Dispatchers.Main to use test dispatcher
        Dispatchers.setMain(testDispatcher) // override Dispatchers.Main for testing cororutines
        // Initialize ViewModel with mocked repository
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        // Reset main dispatcher after test
        Dispatchers.resetMain()
    }

    // Test successful login scenario
    @Test
    fun `login success posts keypass to loginResult`() = runTest {
        val authResponse = AuthResponse("validKeypass")
        // Mock repository to return successful response
        whenever(repository.login("footscray", "user", "pass"))// mock Api responses for success, HTTP error or exception
            .thenReturn(Response.success(authResponse))

        // Call login
        viewModel.login("footscray", "user", "pass")
        // Advance dispatcher to execute coroutine
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.loginResult.value
        assertTrue(result?.isSuccess == true) // Ensure result is success
        assertEquals("validKeypass", result?.getOrNull()) // Keypass matches
    }

    // Test failed login scenario (HTTP error)
    @Test
    fun `login failure posts error to loginResult`() = runTest {
        // Mock repository to return HTTP 401 error
        whenever(repository.login("footscray", "user", "wrongpass"))
            .thenReturn(Response.error(401, "Unauthorized".toResponseBody()))

        viewModel.login("footscray", "user", "wrongpass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.loginResult.value
        assertTrue(result?.isFailure == true) // Should be failure
        assertTrue(result?.exceptionOrNull()?.message?.contains("Login failed") == true)
    }

    // Test exception scenario (network failure)
    @Test
    fun `login exception posts error to loginResult`() = runTest {
        // Mock repository to throw exception
        whenever(repository.login("footscray", "user", "pass"))
            .thenThrow(RuntimeException("Network error"))

        viewModel.login("footscray", "user", "pass")
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.loginResult.value
        assertTrue(result?.isFailure == true) // Should be failure
        assertTrue(result?.exceptionOrNull()?.message?.contains("Network error") == true)
    }
}
