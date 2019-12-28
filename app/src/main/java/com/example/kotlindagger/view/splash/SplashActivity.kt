package com.example.kotlindagger.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.view.login.LoginActivity
import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.viewmodel.SplashViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var splashViewModel: SplashViewModel

    /**
     * If the user is not registered [RegistrationActivity] will be launched,
     * if the user is not logged [LoginActivity] will be launched,
     * else [MainActivity] will be launched.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of SplashComponent by grabbing the factory from the app graph
        // and injects this activity to that component
        (application as MyApplication).appComponent.splashComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.splashState.observe(this, Observer { state ->
            when (state) {
                is Registration -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    finish()
                }
                is Login -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                is Main -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        })

        splashViewModel.start()
    }
}