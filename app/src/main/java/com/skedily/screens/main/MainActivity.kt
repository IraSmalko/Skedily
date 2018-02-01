package com.skedily.screens.main

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityMainBinding
import com.skedily.screens.authentication.LoggedInActivity
import com.skedily.screens.dashboard.DashboardFragment
import com.skedily.screens.family.FamilyFragment
import com.skedily.screens.schedule.ScheduleFragment
import com.skedily.utils.addFragmentToActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by smalk on 11/26/2017.
 */
class MainActivity : BaseBoundVmActivity<ActivityMainBinding, MainViewModel>(
        R.layout.activity_main, MainViewModel::class), MainInteractor {

    private val fragments = mapOf(
            State.DASHBOARD to DashboardFragment(),
            State.SCHEDULE to ScheduleFragment(),
            State.FAMILY to FamilyFragment()
    )

    public override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            startActivity(Intent(this, LoggedInActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragmentToActivity(supportFragmentManager, DashboardFragment(), R.id.container)
        vm.interactor = this

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            vm.state = State.values().first { it.menuId == menuItem.itemId }
            true
        }
    }

    override fun display(state: State?) {
        fragments[state]!!.replaceAndCommit(R.id.container)
    }

}