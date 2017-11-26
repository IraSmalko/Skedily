package com.skedily.screens.main

import com.skedily.base.BaseViewModel
import com.skedily.utils.weak

/**
 * Created by smalk on 11/26/2017.
 */
class MainViewModel : BaseViewModel() {
    var interactor by weak<MainInteractor>()

    fun onClickDashboard() {
        interactor!!.onClickDashboard()
    }

    fun onClickSchedule() {
        interactor!!.onClickSchedule()
    }

    fun onClickFamily() {
        interactor!!.onClickFamily()
    }
}