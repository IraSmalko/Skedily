package com.skedily.utils

import android.app.Application
import android.widget.Toast
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.skedily.BuildConfig


class App : Application() {
    companion object {
        lateinit var app: App
            private set
    }


    private val SHOW_TRUE_KEY = "show_true_key"

    lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        super.onCreate()
        app = this

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        firebaseRemoteConfig.setConfigSettings(configSettings)

        firebaseRemoteConfig.fetch(0).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(app, "Fetch Succeeded",
                        Toast.LENGTH_SHORT).show()

                // After config data is successfully fetched, it must be activated before newly fetched
                // values are returned.
                firebaseRemoteConfig.activateFetched()
            } else {
                Toast.makeText(app, "Fetch Failed",
                        Toast.LENGTH_SHORT).show()
            }
            displayWelcomeMessage()
        }
    }
//                .addOnCompleteListener(this, object : OnCompleteListener<Void> {
//                    override fun onComplete(@NonNull task: Task<Void>) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(app, "Fetch Succeeded",
//                                    Toast.LENGTH_SHORT).show()
//
//                            // After config data is successfully fetched, it must be activated before newly fetched
//                            // values are returned.
//                            firebaseRemoteConfig.activateFetched()
//                        } else {
//                            Toast.makeText(app, "Fetch Failed",
//                                    Toast.LENGTH_SHORT).show()
//                        }
//                        displayWelcomeMessage()
//                    }
//                })

    private fun displayWelcomeMessage() {
        if (firebaseRemoteConfig.getBoolean(SHOW_TRUE_KEY)) {
            Toast.makeText(app, "true",
                    Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(app, "false",
                    Toast.LENGTH_SHORT).show()
        }
    }


}