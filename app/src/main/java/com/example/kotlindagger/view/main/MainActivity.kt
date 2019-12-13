package com.example.kotlindagger.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.model.UserManager
import com.example.kotlindagger.view.login.LoginActivity
import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.view.settings.SettingsActivity
import com.example.kotlindagger.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var userManager: UserManager
    @Inject lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Grabs instance of the application graph
        // and populates @Inject fields with objects from the graph
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        /**
         * If the user is not registered [RegistrationActivity] will be launched,
         * if the user is not logged [LoginActivity] will be launched,
         * else carry on with MainActivity.
         */
        if (!userManager.isUserLogged()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            setContentView(R.layout.activity_main)

            setupViews()
        }
    }

    /**
     * Updating unread notifications onResume because they can get updated on [SettingsActivity].
     */
    override fun onResume() {
        super.onResume()

        notificationsTextView.text = mainViewModel.notificationsText
    }

    private fun setupViews() {
        welcomeTextView.text = mainViewModel.welcomeText

        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}