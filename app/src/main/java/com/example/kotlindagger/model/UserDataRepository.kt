package com.example.kotlindagger.model

import com.example.kotlindagger.app.randomInt
import com.example.kotlindagger.di.LoggedUserScope
import javax.inject.Inject

/**
 * UserDataRepository contains user-specific data such as username and unread notifications.
 *
 * Dagger how to provide instances of this type by @Inject. Dagger also know that [UserManager]
 * is a dependency.
 */
@LoggedUserScope
class UserDataRepository @Inject constructor(private val userManager: UserManager) {
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