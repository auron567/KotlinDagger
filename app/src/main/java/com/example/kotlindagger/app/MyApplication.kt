package com.example.kotlindagger.app

import android.app.Application
import com.example.kotlindagger.model.SharedPreferencesStorage
import com.example.kotlindagger.model.UserManager

open class MyApplication : Application() {
    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}