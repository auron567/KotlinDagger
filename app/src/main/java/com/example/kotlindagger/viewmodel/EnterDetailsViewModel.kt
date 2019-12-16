package com.example.kotlindagger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindagger.app.Event
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsFragment
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsError
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsSuccess
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsViewState
import javax.inject.Inject

/**
 * EnterDetailsViewModel is the ViewModel that [EnterDetailsFragment] uses to
 * validate user's input data.
 *
 * Dagger how to provide instances of this type by @Inject.
 */
class EnterDetailsViewModel @Inject constructor() {

    companion object {
        private const val MIN_LENGTH = 5
    }

    private val _enterDetailsState = MutableLiveData<Event<EnterDetailsViewState>>()
    val enterDetailsState: LiveData<Event<EnterDetailsViewState>>
        get() = _enterDetailsState

    fun validateInput(username: String, password: String) {
        when {
            username.length < MIN_LENGTH ->
                _enterDetailsState.value =
                    Event(EnterDetailsError("Username has to be longer than 4 characters"))
            password.length < MIN_LENGTH ->
                _enterDetailsState.value =
                    Event(EnterDetailsError("Password has to be longer than 4 characters"))
            else ->
                _enterDetailsState.value = Event(EnterDetailsSuccess)
        }
    }
}