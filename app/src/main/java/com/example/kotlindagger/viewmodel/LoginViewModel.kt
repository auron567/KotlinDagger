package com.example.kotlindagger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindagger.model.UserManager
import com.example.kotlindagger.view.login.LoginActivity
import com.example.kotlindagger.view.login.LoginError
import com.example.kotlindagger.view.login.LoginSuccess
import com.example.kotlindagger.view.login.LoginViewState

/**
 * LoginViewModel is the ViewModel that [LoginActivity] uses to validate user login.
 */
class LoginViewModel(private val userManager: UserManager) {
    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun login(username: String, password: String) {
        if (userManager.loginUser(username, password)) {
            _loginState.value = LoginSuccess
        } else {
            _loginState.value = LoginError
        }
    }

    fun unregister() {
        userManager.unregisterUser()
    }

    fun getUsername(): String {
        return userManager.username
    }
}