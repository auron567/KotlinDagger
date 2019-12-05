package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.model.UserDataRepository
import com.example.kotlindagger.model.UserManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest {
    private val mockUserDataRepository: UserDataRepository = mockk(relaxed = true)
    private val mockUserManager: UserManager = mockk(relaxed = true)
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setup() {
        viewModel = SettingsViewModel(mockUserDataRepository, mockUserManager)
    }

    @Test
    fun `RefreshNotifications works as expected`() {
        viewModel.refreshNotifications()

        verify {
            mockUserDataRepository.refreshUnreadNotifications()
        }
    }

    @Test
    fun `Logout works as expected`() {
        viewModel.logout()

        verify {
            mockUserManager.logoutUser()
        }
    }
}