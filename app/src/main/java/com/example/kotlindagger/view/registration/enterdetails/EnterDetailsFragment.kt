package com.example.kotlindagger.view.registration.enterdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.kotlindagger.R
import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.viewmodel.EnterDetailsViewModel
import com.example.kotlindagger.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_enter_details.*
import javax.inject.Inject

/**
 * [RegistrationViewModel] is used to set the username and password (attached to Activity's lifecycle and
 * shared between different fragments).
 *
 * [EnterDetailsViewModel] is used to validate the user's input data (attached to this Fragment's lifecycle).
 */
class EnterDetailsFragment : Fragment() {

    // @Inject annotated fields will be provided by Dagger
    @Inject lateinit var registrationViewModel: RegistrationViewModel
    @Inject lateinit var enterDetailsViewModel: EnterDetailsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Grabs the RegistrationComponent from the Activity and injects this Fragment
        (activity as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterDetailsViewModel.enterDetailsState.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is EnterDetailsSuccess -> {
                        val username = usernameEditText.text.toString()
                        val password = passwordEditText.text.toString()
                        registrationViewModel.updateUserData(username, password)

                        (activity as RegistrationActivity).onDetailsEntered()
                    }
                    is EnterDetailsError -> {
                        errorTextView.text = state.error
                        errorTextView.visibility = View.VISIBLE
                    }
                }
            }
        })

        setupViews()
    }

    private fun setupViews() {
        usernameEditText.doOnTextChanged { _, _, _, _ ->
            errorTextView.visibility = View.INVISIBLE
        }

        passwordEditText.doOnTextChanged { _, _, _, _ ->
            errorTextView.visibility = View.INVISIBLE
        }
        
        nextButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            enterDetailsViewModel.validateInput(username, password)
        }
    }
}