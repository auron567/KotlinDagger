package com.example.kotlindagger.model

import com.example.kotlindagger.app.randomInt

/**
 * UserDataRepository contains user-specific data such as username and unread notifications.
 */
class UserDataRepository(private val userManager: UserManager) {
    val username: String
        get() = userManager.username

    var unreadNotifications: Int

    init {
        unreadNotifications = randomInt()
    }

    fun refreshUnreadNotifications() {
        unreadNotifications = randomInt()
    }
}