package com.skedily.screens.authentication


import android.content.Intent
import com.skedily.base.ActivityResult
import io.reactivex.Maybe

/**
 * Created by smalk on 11/26/2017.
 */
interface LoggedInInteractor {
    fun singIn()
    fun signOut()
    fun successfulLogIn()
    fun failedLogIn()
}