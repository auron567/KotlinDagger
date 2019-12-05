package com.example.kotlindagger.model

/**
 * Handles user lifecycle. Manages registrations, logs in and logs out.
 * Knows when the user is registered and/or logged in.
 */
class UserManager(private val storage: Storage) {

    companion object {
        private const val REGISTERED_USER = "registered_user"
        private const val PASSWORD_SUFFIX = "password"
    }

    var userDataRepository: UserDataRepository? = null

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
        userDataRepository = null
    }

    fun isUserLogged(): Boolean {
        return userDataRepository != null
    }

    private fun userJustLogged() {
        userDataRepository = UserDataRepository(this)
    }
}