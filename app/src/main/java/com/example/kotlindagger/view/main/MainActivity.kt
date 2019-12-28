package com.example.kotlindagger.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.view.settings.SettingsActivity
import com.example.kotlindagger.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Gets the UserManager from the app graph
        val userManager = (application as MyApplication).appComponent.userManager()

        // Gets the instance of UserComponent from the UserManager
        // and injects this activity to that component
        userManager.userComponent!!.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
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