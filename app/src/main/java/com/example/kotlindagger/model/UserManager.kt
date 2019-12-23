package com.example.kotlindagger.model

import com.example.kotlindagger.di.UserComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles user lifecycle. Manages registrations, logs in and logs out.
 * Knows when the user is registered and/or logged in.
 *
 * Dagger how to provide instances of this type by @Inject. Dagger also know that [Storage]
 * is a dependency.
 *
 * Marked with @Singleton since we only one an instance of UserManager in the application graph.
 */
@Singleton
class UserManager @Inject constructor(
    private val storage: Storage,
    private val userComponentFactory: UserComponent.Factory
) {

    companion object {
        private const val REGISTERED_USER = "registered_user"
        private const val PASSWORD_SUFFIX = "password"
    }

    var userComponent: UserComponent? = null
        private set

    val username: String
        get() = storage.getString(REGISTERED_USER)

    fun registerUser(username: String, password: String) {
        storage.setString(REGISTERED_USER, username)
        storage.setString("$username$PASSWORD_SUFFIX", password)

        userJustLogged()
    }

    fun unregisterUser() {
        storage.removeString("$username$PASSWORD_SUFFIX")
        storage.setString(REGISTERED_USER, "")
    }

    fun isUserRegistered(): Boolean {
        return storage.getString(REGISTERED_USER).isNotEmpty()
    }

    fun loginUser(username: String, password: String): Boolean {
        val registeredUsername = this.username
        if (registeredUsername != username) {
            return false
        }

        val registeredPassword = storage.getString("$username$PASSWORD_SUFFIX")
        if (registeredPassword != password) {
            return false
        }

        userJustLogged()
        return true
    }

    fun logoutUser() {
        userComponent = null
    }

    fun isUserLogged(): Boolean {
        return userComponent != null
    }

    private fun userJustLogged() {
        userComponent = userComponentFactory.create()
    }
}