package com.example.kotlindagger.view.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.view.login.LoginActivity
import com.example.kotlindagger.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val userManager = (application as MyApplication).userManager

        settingsViewModel = SettingsViewModel(userManager.userDataRepository!!, userManager)
        setupViews()
    }

    private fun setupViews() {
        refreshButton.setOnClickListener {
            settingsViewModel.refreshNotifications()
        }

        logoutButton.setOnClickListener {
            settingsViewModel.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}