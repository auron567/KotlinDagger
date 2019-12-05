package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.model.UserDataRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private val mockUserDataRepository: UserDataRepository = mockk()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(mockUserDataRepository)
    }

    @Test
    fun `WelcomeText returns right value`() {
        every { mockUserDataRepository.username } returns "Username"

        assertEquals("Hello Username!", viewModel.welcomeText)
    }

    @Test
    fun `NotificationsText returns right value`() {
        every { mockUserDataRepository.unreadNotifications } returns 5

        assertEquals("You have 5 unread notifications", viewModel.notificationsText)
    }
}