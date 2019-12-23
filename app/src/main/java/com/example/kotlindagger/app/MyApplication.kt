package com.example.kotlindagger.app

import android.app.Application
import com.example.kotlindagger.di.AppComponent
import com.example.kotlindagger.di.DaggerAppComponent

open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of the AppComponent using its Factory constructor
        return DaggerAppComponent.factory().create(applicationContext)
    }
}