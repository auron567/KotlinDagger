package com.example.kotlindagger.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of LoginComponent by grabbing the factory from the app graph
        // and injects this activity to that component
        (application as MyApplication).appComponent.loginComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.loginState.observe(this, Observer { state ->
            when (state) {
                is LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginError -> errorTextView.visibility = View.VISIBLE
            }
        })

        setupViews()
    }

    private fun setupViews() {
        usernameEditText.isEnabled = false
        usernameEditText.setText(loginViewModel.getUsername())

        passwordEditText.doOnTextChanged { _, _, _, _ ->
            errorTextView.visibility = View.INVISIBLE
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginViewModel.login(username, password)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_unregister -> {
                loginViewModel.unregister()
                val intent = Intent(this, RegistrationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}