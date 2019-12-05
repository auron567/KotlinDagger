package com.example.kotlindagger.view.registration.enterdetails

sealed class EnterDetailsViewState

object EnterDetailsSuccess : EnterDetailsViewState()

class EnterDetailsError(val error: String) : EnterDetailsViewState()