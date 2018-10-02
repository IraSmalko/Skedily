package com.skedily.utils

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Maybe
import io.reactivex.subjects.PublishSubject

/**
 * Created by smalk on 11/28/2017.
 */
class AuthListener(private val fbAuth: FirebaseAuth, private val credential: AuthCredential) {
    private val result = PublishSubject.create<Boolean>()

    fun awaitResult(): Maybe<Boolean> {
        fbAuth.signInWithCredential(credential).addOnCompleteListener { result.onNext(it.isSuccessful) }
        return result.firstElement()
    }
}