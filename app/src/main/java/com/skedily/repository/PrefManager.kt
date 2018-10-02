package com.skedily.repository

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by smalk on 1/18/2018.
 */
val groupUrl: String = ""

val userId: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""