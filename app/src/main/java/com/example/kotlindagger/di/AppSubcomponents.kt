package com.example.kotlindagger.di

import dagger.Module

// This module tells AppComponent which are its subcomponents
@Module(
    subcomponents = [
        SplashComponent::class,
        RegistrationComponent::class,
        LoginComponent::class,
        UserComponent::class
    ]
)
class AppSubcomponents