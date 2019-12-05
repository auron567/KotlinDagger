package com.example.kotlindagger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlindagger.getOrAwaitValue
import com.example.kotlindagger.model.UserManager
import com.example.kotlindagger.view.login.LoginError
import com.example.kotlindagger.view.login.LoginSuccess
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockUserManager: UserManager = mockk(relaxed = true)
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(mockUserManager)
    }

    @Test
    fun `GetUsername returns right value`() {
        every { mockUserManager.username } returns "Username"

        assertEquals("Username", viewModel.getUsername())
    }

    @Test
    fun `Login succeeds when values are valid`() {
        every { mockUserManager.loginUser(any(), any()) } returns true

        viewModel.login("Username", "Password")

        assertTrue(viewModel.loginState.getOrAwaitValue() is LoginSuccess)
    }

    @Test
    fun `Login gives error when values are invalid`() {
        every { mockUserManager.loginUser(any(), any()) } returns false

        viewModel.login("Username", "Password")

        assertTrue(viewModel.loginState.getOrAwaitValue() is LoginError)
    }

    @Test
    fun `Unregister works as expected`() {
        viewModel.unregister()

        verify {
            mockUserManager.unregisterUser()
        }
    }
}