package com.example.kotlindagger.model

import com.example.kotlindagger.di.UserComponent
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserManagerTest {
    private lateinit var storage: Storage
    private lateinit var userManager: UserManager

    @Before
    fun setup() {
        val mockUserComponentFactory = mockk<UserComponent.Factory>()
        val mockUserComponent = mockk<UserComponent>()
        every { mockUserComponentFactory.create() } returns mockUserComponent

        storage = FakeStorage()
        userManager = UserManager(storage, mockUserComponentFactory)
    }

    @Test
    fun `Username returns right value`() {
        assertEquals("", userManager.username)

        userManager.registerUser("Username", "Password")

        assertEquals("Username", userManager.username)
    }

    @Test
    fun `RegisterUser adds values to the storage`() {
        assertFalse(userManager.isUserRegistered())
        assertFalse(userManager.isUserLogged())

        userManager.registerUser("Username", "Password")

        assertTrue(userManager.isUserRegistered())
        assertTrue(userManager.isUserLogged())
        assertEquals("Username", storage.getString("registered_user"))
        assertEquals("Password", storage.getString("Usernamepassword"))
    }

    @Test
    fun `UnregisterUser removes values to the storage`() {
        userManager.registerUser("Username", "Password")
        userManager.unregisterUser()

        assertFalse(userManager.isUserRegistered())
        assertEquals("", storage.getString("registered_user"))
        assertEquals("", storage.getString("Usernamepassword"))
    }

    @Test
    fun `IsUserRegistered works as expected`() {
        assertFalse(userManager.isUserRegistered())

        userManager.registerUser("Username", "Password")

        assertTrue(userManager.isUserRegistered())
    }

    @Test
    fun `LoginUser succeeds when values are valid`() {
        userManager.registerUser("Username", "Password")
        userManager.logoutUser()

        assertTrue(userManager.loginUser("Username", "Password"))
        assertTrue(userManager.isUserLogged())
    }

    @Test
    fun `LoginUser gives error when when username is invalid`() {
        userManager.registerUser("Username", "Password")
        userManager.logoutUser()

        assertFalse(userManager.loginUser("User", "Password"))
        assertFalse(userManager.isUserLogged())
    }

    @Test
    fun `LoginUser gives error when when password is invalid`() {
        userManager.registerUser("Username", "Password")
        userManager.logoutUser()

        assertFalse(userManager.loginUser("Username", "Pass"))
        assertFalse(userManager.isUserLogged())
    }

    @Test
    fun `LogoutUser works as expected`() {
        userManager.registerUser("Username", "Password")
        userManager.logoutUser()

        assertFalse(userManager.isUserLogged())
    }

    @Test
    fun `isUserLogged works as expected`() {
        assertFalse(userManager.isUserLogged())

        userManager.registerUser("Username", "Password")

        assertTrue(userManager.isUserLogged())
    }
}