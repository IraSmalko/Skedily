package com.skedily.screens.main

import android.os.Bundle
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityMainBinding
import com.skedily.screens.schedule.ScheduleFragment
import com.skedily.utils.addFragmentToActivity

/**
 * Created by smalk on 11/26/2017.
 */
class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
        R.layout.activity_main, MainViewModel::class), MainInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragmentToActivity(getSupportFragmentManager(), ScheduleFragment(), R.id.container)
        vm.interactor = this

    }

    override fun onClickDashboard() {

    }

    override fun onClickSchedule() {

    }

    override fun onClickFamily() {

    }
}