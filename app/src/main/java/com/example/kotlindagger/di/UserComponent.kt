package com.example.kotlindagger.di

import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.view.settings.SettingsActivity
import dagger.Subcomponent

// Scope annotation that the UserComponent uses
// Classes annotated with @LoggedUserScope will have a unique instance in this component
@LoggedUserScope
// Definition of a Dagger subcomponent
@Subcomponent
interface UserComponent {

    // Factory to create instances of the UserComponent
    @Subcomponent.Factory
    interface Factory {

        fun create(): UserComponent
    }

    // Classes than can be injected by this component
    fun inject(activity: MainActivity)

    fun inject(activity: SettingsActivity)
}