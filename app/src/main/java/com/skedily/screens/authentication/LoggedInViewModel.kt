package com.skedily.screens.authentication

import com.google.android.gms.common.SignInButton
import com.skedily.base.BaseViewModel
import com.skedily.utils.weak

/**
 * Created by smalk on 11/26/2017.
 */
class LoggedInViewModel : BaseViewModel() {
    var interactor by weak<LoggedInInteractor>()

    fun init(interactor: LoggedInInteractor, signIn: SignInButton) {
        this.interactor = interactor

        signIn.setOnClickListener({ _ -> signIn() })
    }

    fun signIn() {
        interactor?.singIn()
    }

    fun signOut() {
        interactor?.signOut()
    }

    fun successfulLogIn() = interactor?.successfulLogIn()

    fun failedLogIn() = interactor?.failedLogIn()
}