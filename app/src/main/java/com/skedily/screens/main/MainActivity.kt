package com.skedily.screens.main

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityMainBinding
import com.skedily.screens.authentication.LoggedInActivity
import com.skedily.screens.dashboard.DashboardFragment
import com.skedily.utils.addFragmentToActivity

/**
 * Created by smalk on 11/26/2017.
 */
class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
        R.layout.activity_main, MainViewModel::class), MainInteractor {

    public override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            startActivity(Intent(this, LoggedInActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragmentToActivity(getSupportFragmentManager(), DashboardFragment(), R.id.container)
        vm.interactor = this

    }

    override fun onClickDashboard() {
    }

    override fun onClickSchedule() {

    }

    override fun onClickFamily() {

    }
}