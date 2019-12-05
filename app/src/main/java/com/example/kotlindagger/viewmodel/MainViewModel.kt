package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.model.UserDataRepository

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 */
class MainViewModel(private val userDataRepository: UserDataRepository) {
    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    val notificationsText: String
        get() = "You have ${userDataRepository.unreadNotifications} unread notifications"
}