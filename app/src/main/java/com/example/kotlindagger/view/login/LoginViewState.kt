package com.example.kotlindagger.view.login

sealed class LoginViewState

object LoginSuccess : LoginViewState()

object LoginError : LoginViewState()