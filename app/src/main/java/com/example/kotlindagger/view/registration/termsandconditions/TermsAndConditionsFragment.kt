package com.example.kotlindagger.view.registration.termsandconditions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlindagger.R
import com.example.kotlindagger.view.registration.RegistrationActivity
import com.example.kotlindagger.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_terms_and_conditions.*

/**
 * [RegistrationViewModel] is used to accept TCs (attached to Activity's lifecycle and shared between
 * different fragments).
 */
class TermsAndConditionsFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = (activity as RegistrationActivity).registrationViewModel

        registerButton.setOnClickListener {
            registrationViewModel.acceptTCs()

            (activity as RegistrationActivity).onTermsAndConditionsAccepted()
        }
    }
}