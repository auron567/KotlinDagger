package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.model.UserManager
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class RegistrationViewModelTest {
    private val mockUserManager: UserManager = mockk(relaxed = true)
    private lateinit var viewModel: RegistrationViewModel

    @Before
    fun setup() {
        viewModel = RegistrationViewModel(mockUserManager)
    }

    @Test
    fun `RegisterUser works as expected`() {
        with(viewModel) {
            updateUserData("Username", "Password")
            acceptTCs()
            registerUser()
        }

        verify {
            mockUserManager.registerUser("Username", "Password")
        }
    }
}