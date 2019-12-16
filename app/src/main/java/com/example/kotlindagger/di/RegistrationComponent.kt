package com.example.kotlindagger.di

import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsFragment
import com.example.kotlindagger.view.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

// Scope annotation that the RegistrationComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface RegistrationComponent {

    // Factory to create instances of the RegistrationComponent
    @Subcomponent.Factory
    interface Factory {

        fun create(): RegistrationComponent
    }

    // Classes than can be injected by this component
    fun inject(activity: RegistrationActivity)

    fun inject(fragment: EnterDetailsFragment)

    fun inject(fragment: TermsAndConditionsFragment)
}