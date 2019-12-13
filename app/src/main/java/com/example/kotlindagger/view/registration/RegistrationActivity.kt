package com.example.kotlindagger.view.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindagger.R
import com.example.kotlindagger.app.MyApplication
import com.example.kotlindagger.view.main.MainActivity
import com.example.kotlindagger.view.registration.enterdetails.EnterDetailsFragment
import com.example.kotlindagger.view.registration.termsandconditions.TermsAndConditionsFragment
import com.example.kotlindagger.viewmodel.RegistrationViewModel
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Grabs instance of the application graph
        // and populates @Inject fields with objects from the graph
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, EnterDetailsFragment())
            .commit()
    }

    /**
     * Callback from [EnterDetailsFragment] when username and password has been entered.
     */
    fun onDetailsEntered() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder, TermsAndConditionsFragment())
            .addToBackStack(TermsAndConditionsFragment::class.java.simpleName)
            .commit()
    }

    /**
     * Callback from [TermsAndConditionsFragment] when TCs have been accepted.
     */
    fun onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}