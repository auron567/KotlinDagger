package com.example.kotlindagger.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kotlindagger.getOrAwaitValue
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsError
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsSuccess
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class EnterDetailsViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EnterDetailsViewModel

    @Before
    fun setup() {
        viewModel = EnterDetailsViewModel()
    }

    @Test
    fun `ValidateInput gives error when username is invalid`() {
        viewModel.validateInput("User", "Password")

        assertTrue(viewModel.enterDetailsState.getOrAwaitValue() is EnterDetailsError)
    }

    @Test
    fun `ValidateInput gives error when password is invalid`() {
        viewModel.validateInput("Username", "Pass")

        assertTrue(viewModel.enterDetailsState.getOrAwaitValue() is EnterDetailsError)
    }

    @Test
    fun `ValidateInput succeeds when values are valid`() {
        viewModel.validateInput("Username", "Password")

        assertTrue(viewModel.enterDetailsState.getOrAwaitValue() is EnterDetailsSuccess)
    }
}