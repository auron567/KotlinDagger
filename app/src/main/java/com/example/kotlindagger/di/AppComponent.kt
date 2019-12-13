package com.example.kotlindagger.di

import android.content.Context
import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.view.registration.RegistrationActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Scope annotation that the AppComponent uses
// Classes annotated with @Singleton will have a unique instance in this component
@Singleton
// Definition of a Dagger component that adds info from StorageModule to the graph
@Component(modules = [StorageModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {

        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes than can be injected by this component
    fun inject(activity: RegistrationActivity)

    fun inject(activity: MainActivity)
}