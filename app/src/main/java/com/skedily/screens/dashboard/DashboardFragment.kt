package com.skedily.screens.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.skedily.R
import com.skedily.base.BaseBoundVmFragment
import com.skedily.databinding.FragmentDashboardBinding
import com.skedily.screens.add_card.AddCardActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseBoundVmFragment<FragmentDashboardBinding, DashboardViewModel>(
        R.layout.fragment_dashboard, DashboardViewModel::class), DashboardInteractor {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.interactor = this
        vm.initRecycler(recycler)

    }

    override fun openAddCardScreen() {
        startActivity(Intent(context, AddCardActivity::class.java))
    }

    override fun openTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
