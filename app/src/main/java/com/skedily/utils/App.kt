package com.skedily.utils

import android.app.Application


class App : Application() {
    companion object {
        lateinit var app: Application

    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}