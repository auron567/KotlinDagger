package com.example.kotlindagger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.kotlindagger.view.main.MainActivity
import org.junit.Test

class ApplicationTest {

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        // Should be in Registration/EnterDetails because the user is not registered
        onView(withText("Register to Dagger World!")).check(matches(isDisplayed()))
        onView(withId(R.id.usernameEditText)).perform(typeText("Username"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("Password"), closeSoftKeyboard())
        onView(withId(R.id.nextButton)).perform(click())

        // Registration/TermsAndConditions
        onView(withText("Terms and Conditions")).check(matches(isDisplayed()))
        onView(withId(R.id.registerButton)).perform(click())

        // Main
        onView(withText("Hello Username!")).check(matches(isDisplayed()))
        onView(withId(R.id.settingsButton)).perform(click())

        // Settings
        onView(withId(R.id.refreshButton)).check(matches(isDisplayed()))
        onView(withId(R.id.logoutButton)).perform(click())

        // Login
        onView(withText("Welcome to Dagger World!")).check(matches(isDisplayed()))
        onView(withId(R.id.passwordEditText)).perform(typeText("Password"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        // Main
        onView(withText("Hello Username!")).check(matches(isDisplayed()))
        onView(withId(R.id.settingsButton)).perform(click())

        // Settings
        onView(withId(R.id.refreshButton)).check(matches(isDisplayed()))
        onView(withId(R.id.logoutButton)).perform(click())

        // Login
        openActionBarOverflowOrOptionsMenu(getInstrumentation().context)
        onView(withText("Unregister")).perform(click())

        // Registration/EnterDetails
        onView(withText("Register to Dagger World!")).check(matches(isDisplayed()))
    }
}