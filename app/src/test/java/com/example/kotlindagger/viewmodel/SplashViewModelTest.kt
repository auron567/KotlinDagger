package com.example.kotlindagger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlindagger.getOrAwaitValue
import com.example.kotlindagger.model.UserManager
import com.example.kotlindagger.view.splash.Login
import com.example.kotlindagger.view.splash.Main
import com.example.kotlindagger.view.splash.Registration
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class SplashViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockUserManager: UserManager = mockk()
    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup() {
        viewModel = SplashViewModel(mockUserManager)
    }

    @Test
    fun `Start returns Registration when the user is not registered`() {
        every { mockUserManager.isUserLogged() } returns false
        every { mockUserManager.isUserRegistered() } returns false

        viewModel.start()

        assertTrue(viewModel.splashState.getOrAwaitValue() is Registration)
    }

    @Test
    fun `Start returns Login when the user is not logged`() {
        every { mockUserManager.isUserLogged() } returns false
        every { mockUserManager.isUserRegistered() } returns true

        viewModel.start()

        assertTrue(viewModel.splashState.getOrAwaitValue() is Login)
    }

    @Test
    fun `Start returns Main when the user is logged`() {
        every { mockUserManager.isUserLogged() } returns true

        viewModel.start()

        assertTrue(viewModel.splashState.getOrAwaitValue() is Main)
    }
}