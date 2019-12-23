package com.example.kotlindagger

import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.di.AppComponent
import com.example.kotlindagger.di.DaggerTestAppComponent

/**
 * MyTestApplication will override [MyApplication] in android tests.
 */
class MyTestApplication : MyApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates an instance of the TestAppComponent that injects fakes types
        return DaggerTestAppComponent.create()
    }
}