package com.example.kotlindagger

import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.model.FakeStorage
import com.example.kotlindagger.model.UserManager

class MyTestApplication : MyApplication() {
    override val userManager by lazy {
        UserManager(FakeStorage())
    }
}