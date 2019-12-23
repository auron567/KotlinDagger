package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.view.settings.SettingsActivity
import com.example.kotlindagger.model.UserDataRepository
import com.example.kotlindagger.model.UserManager
import javax.inject.Inject

/**
 * SettingsViewModel is the ViewModel that [SettingsActivity] uses to
 * refresh notifications and user logout.
 *
 * Dagger how to provide instances of this type by @Inject. Dagger also know that
 * [UserDataRepository] and [UserManager] are dependencies.
 */
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
) {

    fun refreshNotifications() {
        userDataRepository.refreshUnreadNotifications()
    }

    fun logout() {
        userManager.logoutUser()
    }
}