package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.model.UserDataRepository
import javax.inject.Inject

/**
 * MainViewModel is the ViewModel that [MainActivity] uses to
 * obtain information of what to show on the screen.
 *
 * Dagger how to provide instances of this type by @Inject. Dagger also know that
 * [UserDataRepository] is a dependency.
 */
class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository) {
    val welcomeText: String
        get() = "Hello ${userDataRepository.username}!"

    val notificationsText: String
        get() = "You have ${userDataRepository.unreadNotifications} unread notifications"
}