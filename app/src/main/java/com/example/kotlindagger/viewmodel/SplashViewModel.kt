package com.example.kotlindagger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindagger.model.UserManager
import com.example.kotlindagger.view.splash.*
import javax.inject.Inject

/**
 * SplashViewModel is the ViewModel that [SplashActivity] uses to
 * handle the logic of what screen needs to be shown when opening the app.
 *
 * Dagger how to provide instances of this type by @Inject. Dagger also know that [UserManager]
 * is a dependency.
 */
class SplashViewModel @Inject constructor(private val userManager: UserManager) {
    private val _splashState = MutableLiveData<SplashViewState>()
    val splashState: LiveData<SplashViewState>
        get() = _splashState

    fun start() {
        if (!userManager.isUserLogged()) {
            if (!userManager.isUserRegistered()) {
                _splashState.value = Registration
            } else {
                _splashState.value = Login
            }
        } else {
            _splashState.value = Main
        }
    }
}