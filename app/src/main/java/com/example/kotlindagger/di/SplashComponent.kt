package com.example.kotlindagger.di

import com.example.kotlindagger.view.splash.SplashActivity
import dagger.Subcomponent

// Scope annotation that the SplashComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface SplashComponent {

    // Factory to create instances of the SplashComponent
    @Subcomponent.Factory
    interface Factory {

        fun create(): SplashComponent
    }

    // Classes than can be injected by this component
    fun inject(activity: SplashActivity)
}