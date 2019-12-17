package com.example.kotlindagger.di

import com.example.kotlindagger.view.login.LoginActivity
import dagger.Subcomponent

// Scope annotation that the LoginComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface LoginComponent {

    // Factory to create instances of the LoginComponent
    @Subcomponent.Factory
    interface Factory {

        fun create(): LoginComponent
    }

    // Classes than can be injected by this component
    fun inject(activity: LoginActivity)
}