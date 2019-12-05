package com.example.kotlindagger.viewmodel

import com.example.kotlindagger.view.settings.SettingsActivity
import com.example.kotlindagger.model.UserDataRepository
import com.example.kotlindagger.model.UserManager

/**
 * SettingsViewModel is the ViewModel that [SettingsActivity] uses to
 * refresh notifications and user logout.
 */
class SettingsViewModel(
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