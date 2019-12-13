package com.example.kotlindagger.app

import android.app.Application
import com.example.kotlindagger.di.AppComponent
import com.example.kotlindagger.di.DaggerAppComponent
import com.example.kotlindagger.model.SharedPreferencesStorage
import com.example.kotlindagger.model.UserManager

open class MyApplication : Application() {
    // Instance of the AppComponent that will be used by all the activities in the project
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}