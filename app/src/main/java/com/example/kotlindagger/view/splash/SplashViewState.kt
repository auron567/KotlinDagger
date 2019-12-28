package com.example.kotlindagger.view.splash

sealed class SplashViewState

object Registration: SplashViewState()

object Login: SplashViewState()

object Main: SplashViewState()